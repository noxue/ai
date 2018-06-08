package com.ai.controller.web.v1;

import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.ai.domain.vo.Message;
import com.ai.service.SimService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    @ApiOperation(value = "新增sim", notes = "新增sim卡信息")
    @ResponseBody
    @PostMapping("/add")
    public Message addSim(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Sim sim = new Sim();
        String number = params.get("number");
        String description = params.get("description");
        String gateway_id = params.get("gateway_id");
        String user_id = params.get("user_id");
        if (StringUtils.isEmpty(number) || StringUtils.isEmpty(gateway_id)
                || StringUtils.isEmpty(user_id)|| StringUtils.isEmpty(description)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }
        sim.setDescription(description);
        sim.setGatewayId(Long.parseLong(gateway_id));
        sim.setNumber(number);
        sim.setUserId(user_id);

        if (simService.registerSim(sim)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/add", "addSim", (short) 3003, "新增成功"));
            return new Message().ok(4002, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/add", "addSim", (short) 3004, "新增失败"));
            return new Message().ok(4003, "新增失败");
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
        String gateway_id = params.get("gateway_id");
        String user_id = params.get("user_id");
        String id = params.get("id");
        if (StringUtils.isEmpty(number)|| StringUtils.isEmpty(description)|| StringUtils.isEmpty(id)
                || StringUtils.isEmpty(gateway_id)|| StringUtils.isEmpty(user_id)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }
        sim.setId(Long.parseLong(id));
        sim.setNumber(number);
        sim.setDescription(description);
        sim.setUserId(user_id);
        sim.setGatewayId(Long.parseLong(gateway_id));
        if (simService.editSim(sim)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/edit", "editSim", (short) 4004, "编辑成功"));
            return new Message().ok(4004, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/edit", "editSim", (short) 4005, "编辑失败"));
            return new Message().ok(4005, "编辑失败");
        }
    }

    @ApiOperation(value = "删除sim", notes = "删除sim卡信息")
    @ResponseBody
    @PostMapping("/del")
    public Message delSim(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }

        if (simService.delSim(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/del", "delSim", (short) 4006, "删除成功"));
            return new Message().ok(4006, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/del", "delSim", (short) 4007, "删除失败"));
            return new Message().ok(4007, "删除失败");
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

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String uid =params.get("uid");
        if(simService.findAllSim(pageNum,pageSize,uid)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/all", "findAllSim", (short) 4008, "查询成功"));
            return new Message().ok(4008, "查询成功").addData("simList",simService.findAllSim(pageNum,pageSize,uid));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/all", "simList", (short) 4009, "查询失败"));
            return new Message().ok(4009, "查询失败");
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
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }
        Sim sim = simService.getSimById(Long.parseLong(id));
        if (sim !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/select", "selectSimById", (short) 3010, "查询成功"));
            return new Message().ok(3010, "查询成功").addData("sim",sim);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/select", "selectSimById", (short) 3011, "查询失败"));
            return new Message().ok(3011, "查询失败");
        }
    }



    @ApiOperation(value = "新增simUser", notes = "新增simUser信息")
    @ResponseBody
    @PostMapping("user/add")
    public Message addSimUser(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        SimUser simUser = new SimUser();
        String user_id = params.get("user_id");
        String sim_id = params.get("sim_id");
        if (StringUtils.isEmpty(user_id) || StringUtils.isEmpty(sim_id)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }
        simUser.setSimId(Long.parseLong(sim_id));
        simUser.setUserId(user_id);

        if (simService.registerSimUser(simUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/add", "addSimUser", (short) 4002, "新增成功"));
            return new Message().ok(4002, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/add", "addSimUser", (short) 4003, "新增失败"));
            return new Message().ok(4003, "新增失败");
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
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }
        simUser.setId(Long.parseLong(id));
        simUser.setSimId(Long.parseLong(sim_id));
        simUser.setUserId(user_id);
        if (simService.editSimUser(simUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/edit", "editSimUser", (short) 4004, "编辑成功"));
            return new Message().ok(4004, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/edit", "editSimUser", (short) 4005, "编辑失败"));
            return new Message().ok(4005, "编辑失败");
        }
    }


    @ApiOperation(value = "删除simUser", notes = "删除simUser信息")
    @ResponseBody
    @PostMapping("/user/del")
    public Message delSimUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(4001, "信息不足");
        }

        if (simService.delSimUser(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/del", "delSimUser", (short) 4006, "删除成功"));
            return new Message().ok(4006, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/user/del", "delSimUser", (short) 4007, "删除失败"));
            return new Message().ok(4007, "删除失败");
        }
    }
}
