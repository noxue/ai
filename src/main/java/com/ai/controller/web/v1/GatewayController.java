package com.ai.controller.web.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.GatewayReport;
import com.ai.domain.vo.Message;
import com.ai.service.AccountService;
import com.ai.service.GatewayReportService;
import com.ai.service.GatewayService;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Pattern;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,patch部分更新,del删除
 * @2018年6月4日9:55:36
 */
@RestController
@RequestMapping("/web/api/v1/gateway")
public class GatewayController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private GatewayReportService gatewayReportService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "新增Gateway", notes = "增加一个新的、新增Gateway")
    @ResponseBody
    @PostMapping("/add")
    public Message addGateway(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Gateway gateway = new Gateway();
        String name = params.get("name");
        String description = params.get("description");
        String ip = params.get("ip");
        String port = params.get("port");
        String user_id = params.get("userId");
        String app_id = params.get("app_id");
        if (StringUtils.isEmpty(name) ||StringUtils.isEmpty(ip) ||StringUtils.isEmpty(port)
                ||StringUtils.isEmpty(user_id) ||StringUtils.isEmpty(app_id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3100, "网关信息缺失");
        }
        if (gatewayService.isGateExistByName(name)) {
            // name已存在
            return new Message().error(3150, "名称已被占用");
        }
        if (!accountService.isAccountExistByUid(user_id)) {
            // 查找用户信息
            return new Message().error(3151, "用户不存在");
        }
       String regex =   "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        if(!Pattern.matches(regex, ip)){
            return new Message().error(3152, "请输入正确的ip");
        }
        try{
            gateway.setPort(Integer.parseInt(port));
        }catch (NumberFormatException e){
            return new Message().error(3153, "请输入正确的端口号");
        }
        gateway.setName(name);
        gateway.setDescription(description);
        gateway.setIp(ip);
        gateway.setUserId(user_id);
        gateway.setAppId(Long.parseLong(app_id));
        if (gatewayService.registerGate(gateway)) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3103, "新增失败");
        }
    }

    @ApiOperation(value = "编辑gateway", notes = "编辑gateway信息，修改后的name不可重复")
    @ResponseBody
    @PostMapping("/edit")
    public Message editGate(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Gateway gateway = new Gateway();
        String id = params.get("id");
        String name = params.get("name");
        String description = params.get("description");
        String ip = params.get("ip");
        String port = params.get("port");
        String user_id = params.get("userId");
        if (StringUtils.isEmpty(name) ||StringUtils.isEmpty(ip) ||StringUtils.isEmpty(port)
                ||StringUtils.isEmpty(user_id)||StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3104, "网关信息缺失");
        }
        if (!accountService.isAccountExistByUid(user_id)) {
            // 判断该用户是否存在
            return new Message().error(3101, "用户不存在");
        }
        String regex =  "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                        +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        if(!Pattern.matches(regex, ip)){
            return new Message().error(3152, "请输入正确的ip");
        }
        try{
            gateway.setPort(Integer.parseInt(port));
        }catch (NumberFormatException e){
            return new Message().error(3153, "请输入正确的端口号");
        }
        gateway.setId(Long.parseLong(id));
        gateway.setName(name);
        gateway.setDescription(description);
        gateway.setIp(ip);
        gateway.setUserId(user_id);
        if (gatewayService.editGate(gateway)) {
            redisTemplate.opsForValue().set("gateway_"+ gateway.getId(), "edit");
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3106, "编辑失败");
        }
    }

    @ApiOperation(value = "删除gate", notes = "删除gate信息")
    @ResponseBody
    @PostMapping("/del")
    public Message delGate(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3107, "网关信息缺失");
        }
        if (gatewayService.delGate(Long.parseLong(id))) {
            redisTemplate.opsForValue().set("gateway_"+ id, "del");
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3109, "删除失败");
        }
    }

    /* *
     * @Description 条件分页获取所有gateway信息
     * @Param [] uid
     * @Return com.ai.domain.bo.条件分页获取所有gateway.java
     */
    @ApiOperation(value = "分页获取gateway", notes = "模糊查询分页获取gateway信息")
    @ResponseBody
    @PostMapping("/all")
    public Object findAllGate(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                    int pageSize){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        //uid非必要参数，未做非空判断
        String uid =request.getHeader("appId");
        String name =params.get("name");
        String username =params.get("username");
        if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3017, "网关信息缺失");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = username;
        }
        pageNum = Integer.parseInt(params.get("page"));
        PageInfo<Gateway> gatewayList= gatewayService.findAllGate(pageNum,pageSize,name,uid);
        if( gatewayList!=null ){
            return new Message().ok(0, "success").addData("gatewayList",gatewayList);
        } else {
            return new Message().error(3111, "查询失败");
        }
    }

    @ApiOperation(value = "分页获取gateway", notes = "模糊查询分页获取gateway信息")
    @ResponseBody
    @PostMapping("/listByUid")
    public Object findAllGatelistByUid(HttpServletRequest request, HttpServletResponse response){
        //uid非必要参数，未做非空判断
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3017, "网关信息缺失");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = "";
        }
        PageInfo<Gateway> gatewayList = gatewayService.findAllGate(0,100000,"",uid);
        if(gatewayList!=null){
            return new Message().ok(0, "success").addData("gatewayList",gatewayList);
        } else {
            return new Message().error(3111, "查询失败");
        }
    }
    /* *
     * @Description 根据id获取Gateway信息
     * @Param [] id
     * @Return com.ai.domain.bo.Gateway.java
     */
    @ApiOperation(value = "获取Gateway", notes = "根据id获取Gateway信息")
    @ResponseBody
    @PostMapping("/select")
    public Object selectGatewayById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3107, "网关信息缺失");
        }
        Gateway gateway = gatewayService.getGateById(Long.parseLong(id));
        if (gateway !=null){
            return new Message().ok(0, "success").addData("gateway",gateway);
        } else {
            return new Message().error(3111, "查询失败");
        }
    }

    @ApiOperation(value = "新增网关报告", notes = "新增网关报告信息")
    @ResponseBody
    @PostMapping("/report/add")
    public Message addGatewayReport(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        GatewayReport gatewayReport = new GatewayReport();
        String gatewayId = params.get("gatewayId");
        String detail = params.get("detail");
        String description = params.get("description");
        if (StringUtils.isEmpty(description) || StringUtils.isEmpty(gatewayId)) {
            //验证信息
            return new Message().error(3112, "网关信息缺失");
        }
        gatewayReport.setDescription(description);
        gatewayReport.setGatewayId(Long.parseLong(gatewayId));
        gatewayReport.setDetail(detail);
        if (gatewayReportService.registerGatewayReport(gatewayReport)) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3114, "新增失败");
        }
    }

    @ApiOperation(value = "删除gatewayReport", notes = "删除gatewayReport信息")
    @ResponseBody
    @PostMapping("/report/del")
    public Message delGatewayReport(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3107, "网关信息缺失");
        }
        //执行删除操作
        if (gatewayReportService.delReport(Long.parseLong(id))) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3116, "删除失败");
        }
    }

    /* *
     * @Description 根据id获取GatewayReport信息
     * @Param [] id
     * @Return com.ai.domain.bo.GatewayReport.java
     */
    @ApiOperation(value = "获取GatewayReport", notes = "根据id获取GatewayReport信息")
    @ResponseBody
    @PostMapping("/report/select")
    public Object selectGatewayReportById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3107, "网关信息缺失");
        }
        GatewayReport gatewayReport = gatewayReportService.getGatewayReoprtById(Long.parseLong(id));
        if (gatewayReport !=null){
            return new Message().ok(0, "success").addData("gatewayReport",gatewayReport);
        } else {
            return new Message().error(3118, "查询失败");
        }
    }

    /* *
     * @Description 条件分页获取所有gatewayReport信息
     * @Param []
     * @Return com.ai.domain.bo.条件分页获取所有gatewayReport信息
     */
    @ApiOperation(value = "分页获取gatewayReport", notes = "模糊查询分页获取gatewayReport信息")
    @ResponseBody
    @PostMapping("/report/all")
    public Object findAllGatewayReport(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                      int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                                      int pageSize){

        PageInfo<GatewayReport> gatewayReportList = gatewayReportService.findAllGatewayReport(pageNum,pageSize);
        if(gatewayReportList!=null){
            return new Message().ok(0, "success").addData("gatewayReportList",gatewayReportList);
        } else {
            return new Message().error(3120, "查询失败");
        }
    }

}
