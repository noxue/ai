package com.ai.controller.web.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.ai.domain.vo.Message;
import com.ai.service.AccountService;
import com.ai.service.GatewayService;
import com.ai.service.SimService;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


    @Autowired
    private SimService simService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private StringRedisTemplate redisTemplate;

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
            return new Message().error(3151, "用户不存在");
        }
       // String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
       // String regex = "/^\\d{8,}$/";
        String regex="^[0-9]{1,7}$";
        if(Pattern.matches(regex, number)){
            return new Message().error(4001, "请输入正确的手机号码");
        }
        sim.setDescription(description);
        sim.setGatewayId(Long.parseLong(gateway_id));
        sim.setNumber(number);
        sim.setUserId(user_id);
        if (simService.registerSim(sim)) {
            // 通知指定客户端，哪个网关下添加了sim卡
            Gateway gateway = gatewayService.getGateById(sim.getGatewayId());

            String v = redisTemplate.opsForValue().get("sim_add_"+gateway.getAppId());
            List<String> arr = new ArrayList<>();
            if (v!=null) {
                arr = new ArrayList<String>(Arrays.asList(v.split(",")));
            }
            arr.add(gateway.getId()+"");
            redisTemplate.opsForValue().set("sim_add_"+gateway.getAppId(), String.join(",", org.apache.commons.lang.StringUtils.join(arr.toArray(),",")));

            return new Message().ok(0, "success");
        } else {
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
        if(!StringUtils.isEmpty(user_id)){
            if (!accountService.isAccountExistByUid(user_id)) {
                // 验证用户是否存在
                return new Message().error(3151, "用户不存在");
            }
            sim.setUserId(user_id);
        }
        sim.setId(Long.parseLong(id));
        // String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        String regex="^[0-9]{1,7}$";
        if(Pattern.matches(regex, number)){
            return new Message().error(4001, "请输入正确的手机号码");
        }
        sim.setNumber(number);
        if(!StringUtils.isEmpty(description)){
            sim.setDescription(description);
        }
        if(!StringUtils.isEmpty(gateway_id)){
            sim.setGatewayId(Long.parseLong(gateway_id));
        }

        if (simService.editSim(sim)) {
            redisTemplate.opsForValue().set("sim_"+ id, "edit");
            return new Message().ok(0, "success");
        } else {
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
            redisTemplate.opsForValue().set("sim_"+ id, "del");
            return new Message().ok(0, "success");
        } else {
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
            return new Message().error(4004, "当前用户未登录");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = "";
        }
        PageInfo<Sim> simpPage = simService.findAllSim(pageNum,pageSize,uid ,phone);
        if(simpPage!=null){
            return new Message().ok(0, "success").addData("simList",simpPage);
        } else {
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
            return new Message().error(4004, "当前用户未登录");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = "";
        }
        PageInfo<Sim> simpUserPage = simService.findSimUserById(1,1000,uid);
        if(simpUserPage!=null){
            return new Message().ok(0, "success").addData("simpUserPage",simpUserPage);
        } else {
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
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id  =  params.get("simId");
        PageInfo<SimUser> simpUserList = simService.findSimUserBySimId(1,1000,id);
        if(simpUserList!=null){
            return new Message().ok(0, "success").addData("simpUserList",simpUserList);
        } else {
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
            return new Message().ok(1, "success").addData("sim",sim);
        } else {
            return new Message().error(4012, "查询失败");
        }
    }

    @ApiOperation(value = "新增simUser", notes = "新增simUser信息")
    @ResponseBody
    @PostMapping("user/add")
    public Message addSimUser(HttpServletRequest request, HttpServletResponse response){
        String appid = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        SimUser simUser = new SimUser();
        String user_id = params.get("userId");
        String sim_id = params.get("simId");
        if (StringUtils.isEmpty(user_id) || StringUtils.isEmpty(sim_id)) {
            // 必须信息缺一不可,返回信息不足
            return new Message().error(4013, "信息不足");
        }
        simUser.setSimId(Long.parseLong(sim_id));
        if(accountService.getUserByUidAndPid(user_id,appid )== null){
            // 验证用户是否存在
            return new Message().error(4114, "分配失败,请检查用户名");
        }
        if(simService.isExistInSimUser(user_id,sim_id)){
            return new Message().error(4115, "分配失败,该号码已分配给此用户");
        }
        simUser.setUserId(user_id);
        if (simService.registerSimUser(simUser)) {
            return new Message().ok(0, "success");
        } else {
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
            return new Message().ok(0, "success");
        } else {
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
            return new Message().ok(0, "success");
        } else {
            return new Message().error(4020, "删除失败");
        }
    }
}
