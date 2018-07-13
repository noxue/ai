package com.ai.controller.robot.v1;

import com.ai.domain.bo.UserConfig;
import com.ai.domain.vo.Message;
import com.ai.service.UserConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController("RobotConfigController")
@RequestMapping("/robot/api/v1/userConfig")
public class UserConfigController {

    @Autowired
    private UserConfigService userConfigService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ResponseBody
    @GetMapping("/user")
    public Message GetUserConfig(String id){

        if(StringUtils.isEmpty(id)){
            return new Message().error(8005, "缺少参数 id");
        }
        UserConfig userConfig =  userConfigService.getConfigByUserId(id,"schedule");
        if(userConfig!=null){
            return new Message().ok(0, "success").addData("userConfig",userConfig);
        } else {
            return new Message().error(8006, "查询失败");
        }
    }


}
