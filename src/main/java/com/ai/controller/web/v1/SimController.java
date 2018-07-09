package com.ai.controller.web.v1;

import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.ai.domain.vo.Message;
import com.ai.service.AccountService;
import com.ai.service.SimService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Pattern;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,del删除
 * @2018年6月8日8:49:43
 */
@RestController
@RequestMapping("/web/api/v1/sim")
public class SimController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(SimController.class);

    @Autowired
    private SimService simService;

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "新增sim", notes = "新增sim卡信息")
    @ResponseBody
    @PostMapping("/add")
    public Message addSim(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Sim sim = new Sim();
        String number = params.get("number");
        String description = params.get("description");
        String gateway_id = params.get("gatewayId");
        String user_id = params.get("userId");
        if (StringUtils.isEmpty(number) || StringUtils.isEmpty(gateway_id)
                || StringUtils.isEmpty(user_id)|| StringUtils.isEmpty(description)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4000, "信息不足");
        }
        if (!accountService.isAccountExistByUid(user_id)) {
            // name已存在
            return new Message().error(3101, "用户不存在");
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if(!Pattern.matches(regex, number)){
            return new Message().error(3101, "请输入正确的手机号码");
        }
        sim.setDescription(description);
        sim.setGatewayId(Long.parseLong(gateway_id));
        sim.setNumber(number);
        sim.setUserId(user_id);

        if (simService.registerSim(sim)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/add", "addSim", (short) 3003, "新增成功"));
            return new Message().ok(4001, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/add", "addSim", (short) 3004, "新增失败"));
            return new Message().error(4002, "新增失败");
        }
    }

    @ApiOperation(value = "编辑sim", notes = "修改sim卡信息")
    @ResponseBody
    @PostMapping("/edit")
    public Message editSim(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Sim sim = new Sim();
        String number = params.get("number");
        String description = params.get("description");
        String gateway_id = params.get("gatewayId");
        String user_id = params.get("userId");
        String id = params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4003, "信息不足");
        }
        if(!StringUtils.isEmpty(user_id)&& user_id!=null){
            if (!accountService.isAccountExistByUid(user_id)) {
                // 验证用户是否存在
                return new Message().error(3101, "用户不存在");
            }
            sim.setUserId(user_id);
        }
        sim.setId(Long.parseLong(id));
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if(!Pattern.matches(regex, number)){
            return new Message().error(3101, "请输入正确的手机号码");
        }
        sim.setNumber(number);
        if(!StringUtils.isEmpty(description)){
            sim.setDescription(description);
        }
        if(!StringUtils.isEmpty(gateway_id)){
            sim.setGatewayId(Long.parseLong(gateway_id));
        }

        if (simService.editSim(sim)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/edit", "editSim", (short) 4004, "编辑成功"));
            return new Message().ok(4004, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/edit", "editSim", (short) 4005, "编辑失败"));
            return new Message().error(4005, "编辑失败");
        }
    }

    @ApiOperation(value = "删除sim", notes = "删除sim卡信息")
    @ResponseBody
    @PostMapping("/del")
    public Message delSim(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4006, "信息不足");
        }

        if (simService.delSim(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/del", "delSim", (short) 4006, "删除成功"));
            return new Message().ok(4007, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/del", "delSim", (short) 4007, "删除失败"));
            return new Message().error(4008, "删除失败");
        }
    }

    /* *
     * @Description 分页获取所有sim
     * @Param [] uid 当前登陆用户的uid
     * @Return com.ai.domain.bo.sim.java
     */
    @ApiOperation(value = "分页获取sim", notes = "根据当前用户分页查询获取sim卡信息")
    @ResponseBody
    @PostMapping("/all")
    public Object findAllSim(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                    int pageSize){
        String uid =request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String phone = params.get("number");
        pageNum = Integer.parseInt(params.get("page"));
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3017, "信息缺失");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = "";
        }
        PageInfo<Sim> simpPage = simService.findAllSim(pageNum,pageSize,uid ,phone);
        if(simpPage!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/all", "findAllSim", (short) 4008, "查询成功"));
            return new Message().ok(4009, "查询成功").addData("simList",simpPage);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/all", "findAllSim", (short) 4009, "查询失败"));
            return new Message().error(4010, "查询失败");
        }
    }

    @ApiOperation(value = "获取sim卡信息", notes = "根据当前用户查询simUser信息")
    @ResponseBody
    @PostMapping("/listById")
    public Object findAllSimById(HttpServletRequest request, HttpServletResponse response){
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3017, "信息缺失");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = "";
        }
        PageInfo<Sim> simpUserPage = simService.findSimUserById(1,500,uid);
        if(simpUserPage!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/listById", "findAllSimById", (short) 4008, "查询成功"));
            return new Message().ok(4009, "查询成功").addData("simpUserPage",simpUserPage);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/listById", "findAllSimById", (short) 4009, "查询失败"));
            return new Message().error(4010, "查询失败");
        }
    }

    @ApiOperation(value = "获取simUser信息", notes = "根据当前simId查询simUser信息")
    @ResponseBody
    @PostMapping("/listBySimId")
    public Object findSimUserBySimId(HttpServletRequest request, HttpServletResponse response){
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3017, "信息缺失");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id  =  params.get("simId");
        PageInfo<SimUser> simpUserList = simService.findSimUserBySimId(1,500,id);
        if(simpUserList!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/listBySimId", "findSimUserBySimId", (short) 4008, "查询成功"));
            return new Message().ok(4009, "查询成功").addData("simpUserList",simpUserList);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/listBySimId", "findSimUserBySimId", (short) 4009, "查询失败"));
            return new Message().error(4010, "查询失败");
        }
    }
    /* *
     * @Description 根据id获取sim信息
     * @Param [] id
     * @Return com.ai.domain.bo.sim.java
     */
    @ApiOperation(value = "获取sim", notes = "根据id获取sim卡信息")
    @ResponseBody
    @PostMapping("/select")
    public Object selectSimById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4006, "信息不足");
        }
        Sim sim = simService.getSimById(Long.parseLong(id));
        if (sim !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/select", "selectSimById", (short) 4011, "查询成功"));
            return new Message().ok(4011, "查询成功").addData("sim",sim);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/select", "selectSimById", (short) 4012, "查询失败"));
            return new Message().error(4012, "查询失败");
        }
    }

    @ApiOperation(value = "新增simUser", notes = "新增simUser信息")
    @ResponseBody
    @PostMapping("user/add")
    public Message addSimUser(HttpServletRequest request, HttpServletResponse response){
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        SimUser simUser = new SimUser();
        String user_id = params.get("userId");
        String sim_id = params.get("simId");
        if (StringUtils.isEmpty(user_id) || StringUtils.isEmpty(sim_id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4013, "信息不足");
        }
        simUser.setSimId(Long.parseLong(sim_id));
        if(accountService.getUserByUidAndPid(user_id,appId )== null){
            // 验证用户是否存在
            return new Message().error(3101, "分配失败,请检查用户名");
        }
        if(simService.isExistInSimUser(user_id,sim_id)){
            return new Message().error(3101, "分配失败,该号码已分配给此用户");
        }
        simUser.setUserId(user_id);
        if (simService.registerSimUser(simUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/add", "addSimUser", (short) 4014, "新增成功"));
            return new Message().ok(4014, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/add", "addSimUser", (short) 4015, "新增失败"));
            return new Message().error(4015, "新增失败");
        }
    }

    @ApiOperation(value = "编辑simUser", notes = "修改simUser卡信息")
    @ResponseBody
    @PostMapping("/user/edit")
    public Message editSimUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        SimUser simUser = new SimUser();
        String sim_id = params.get("sim_id");
        String user_id = params.get("user_id");
        String id = params.get("id");
        if (StringUtils.isEmpty(sim_id)|| StringUtils.isEmpty(user_id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4016, "信息不足");
        }
        simUser.setId(Long.parseLong(id));
        simUser.setSimId(Long.parseLong(sim_id));
        simUser.setUserId(user_id);
        if (simService.editSimUser(simUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/edit", "editSimUser", (short) 4004, "编辑成功"));
            return new Message().ok(4017, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/edit", "editSimUser", (short) 4005, "编辑失败"));
            return new Message().error(4018, "编辑失败");
        }
    }


    @ApiOperation(value = "删除simUser", notes = "删除simUser信息")
    @ResponseBody
    @PostMapping("/user/del")
    public Message delSimUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4006, "信息不足");
        }

        if (simService.delSimUser(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/del", "delSimUser", (short) 4006, "删除成功"));
            return new Message().ok(4019, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/del", "delSimUser", (short) 4007, "删除失败"));
            return new Message().error(4020, "删除失败");
        }
    }
}
