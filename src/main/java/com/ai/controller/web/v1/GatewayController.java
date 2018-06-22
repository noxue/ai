package com.ai.controller.web.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.GatewayReport;
import com.ai.domain.vo.Message;
import com.ai.service.GatewayReportService;
import com.ai.service.GatewayService;
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
        String user_id = params.get("user_id");
        String app_id = params.get("app_id");
        if (StringUtils.isEmpty(name) ||StringUtils.isEmpty(ip) ||StringUtils.isEmpty(port)
                ||StringUtils.isEmpty(user_id) ||StringUtils.isEmpty(app_id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3100, "网关信息缺失");
        }
        if (gatewayService.isGateExistByName(name)) {
            // name已存在
            return new Message().error(3101, "名称已被占用");
        }

        gateway.setName(name);
        gateway.setDescription(description);
        gateway.setIp(ip);
        gateway.setPort(Integer.parseInt(port));
        gateway.setUserId(user_id);
        gateway.setAppId(Long.parseLong(app_id));

        if (gatewayService.registerGate(gateway)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/add", "addGateway", (short) 3101, "新增成功"));
            return new Message().ok(3102, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/add", "addGateway", (short) 3102, "新增失败"));
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
        String user_id = params.get("user_id");
        String app_id = params.get("app_id");
        if (StringUtils.isEmpty(name) ||StringUtils.isEmpty(ip) ||StringUtils.isEmpty(port)
                ||StringUtils.isEmpty(user_id) ||StringUtils.isEmpty(app_id) ||StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(3104, "网关信息缺失");
        }
        if (gatewayService.isGateExistByName(name)) {
            // name已存在
            return new Message().error(3001, "名称已存在");
        }
        gateway.setId(Long.parseLong(id));
        gateway.setName(name);
        gateway.setDescription(description);
        gateway.setIp(ip);
        gateway.setPort(Integer.parseInt(port));
        gateway.setUserId(user_id);
        gateway.setAppId(Long.parseLong(app_id));
        if (gatewayService.editGate(gateway)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/edit", "editGate", (short) 3106, "编辑成功"));
            return new Message().ok(3015, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/edit", "editGate", (short) 3107, "编辑失败"));
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
            return new Message().error(3017, "网关信息缺失");
        }

        if (gatewayService.delGate(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/del", "delGate", (short) 3108, "删除成功"));
            return new Message().ok(3108, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/del", "delGate", (short) 3109, "删除失败"));
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
        String uid =params.get("uid");
        if(gatewayService.findAllGate(pageNum,pageSize,uid)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/all", "findAllGate", (short) 3110, "查询成功"));
            return new Message().ok(3110, "查询成功").addData("gatewayList",gatewayService.findAllGate(pageNum,pageSize,uid));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/all", "findAllGate", (short) 3111, "查询失败"));
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
            return new Message().error(3105, "网关信息缺失");
        }
        Gateway gateway = gatewayService.getGateById(Long.parseLong(id));
        if (gateway !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/select", "selectGatewayById", (short) 3010, "查询成功"));
            return new Message().ok(3010, "查询成功").addData("gateway",gateway);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/select", "selectGatewayById", (short) 3011, "查询失败"));
            return new Message().error(3011, "查询失败");
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
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/add", "addGatewayReport", (short) 3101, "新增成功"));
            return new Message().ok(3103, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/add", "addGatewayReport", (short) 3102, "新增失败"));
            return new Message().error(3104, "新增失败");
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
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/del", "delGatewayReport", (short) 3108, "删除成功"));
            return new Message().ok(3115, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/del", "delGatewayReport", (short) 3109, "删除失败"));
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
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/select", "selectGatewayReportById", (short) 3017, "查询成功"));
            return new Message().ok(3017, "查询成功").addData("gatewayReport",gatewayReport);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/select", "selectGatewayReportById", (short) 3018, "查询失败"));
            return new Message().error(3018, "查询失败");
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
        if(gatewayReportService.findAllGatewayReport(pageNum,pageSize)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/all", "findAllGatewayReport", (short) 3110, "查询成功"));
            return new Message().ok(3103, "查询成功").addData("gatewayReportList",gatewayReportService.findAllGatewayReport(pageNum,pageSize));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/report/all", "findAllGatewayReport", (short) 3111, "查询失败"));
            return new Message().error(3104, "查询失败");
        }
    }

}
