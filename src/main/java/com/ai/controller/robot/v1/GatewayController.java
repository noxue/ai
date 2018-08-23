package com.ai.controller.robot.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.vo.Message;
import com.ai.service.GatewayService;
import com.ai.support.factory.LogTaskFactory;
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
@RequestMapping("/robot/api/v1/")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @ApiOperation(value = "获取gateway", notes = "根据appId查询gateway信息")
    @ResponseBody
    @GetMapping("gateways")
    public Message getGatewaysByAppId(HttpServletRequest request){

        String appId = request.getHeader("appid");
        if(StringUtils.isEmpty(appId)){
            return new Message().error(3107, "缺少参数 appid");
        }
        PageInfo<Gateway> gatewayList = gatewayService.findGatewaysByAppId(1,1000,appId);
        if(gatewayList!=null){
            return new Message().ok(0, "查询成功").addData("gateways",gatewayList.getList());
        } else {
            return new Message().error(3104, "查询失败");
        }
    }


}
