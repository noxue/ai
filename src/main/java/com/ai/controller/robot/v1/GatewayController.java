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
                                               int pageSize) {

        String appId = request.getHeader("appid");
        if (StringUtils.isEmpty(appId)) {
            return new Message().error(3107, "缺少参数 id");
        }
        PageInfo<Gateway> gatewayList = gatewayService.findGatewaysByAppId(pageNum, pageSize, appId);
        if (gatewayList != null) {
            return new Message().ok(0, "success").addData("gatewayList", gatewayList.getList());
        } else {
            return new Message().error(8007, "查询失败");
        }
    }


}
