package com.ai.controller.robot.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.vo.Message;
import com.ai.service.GatewayService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController("RobotGatewayController")
@RequestMapping("/robot/api/v1/gateway")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @ApiOperation(value = "分页获取gateway", notes = "根据appId查询gateway信息")
    @ResponseBody
    @GetMapping("/all")
    public Message findGatewaysByAppId(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                              int pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "1500")
                                              int pageSize){

        String appId = request.getHeader("appid");
        if(StringUtils.isEmpty(appId)){
            return new Message().error(3107, "缺少参数 id");
        }
        PageInfo<Gateway> gatewayList = gatewayService.findGatewaysByAppId(pageNum,pageSize,appId);
        if(gatewayList!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/ByAppId", "findGatewaysByAppId", (short) 3110, "查询成功"));
            return new Message().ok(3103, "查询成功").addData("gatewayList",gatewayList.getList());
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/gateway/ByAppId", "findGatewaysByAppId", (short) 3111, "查询失败"));
            return new Message().error(3104, "查询失败");
        }
    }


}
