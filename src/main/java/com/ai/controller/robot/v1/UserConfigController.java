package com.ai.controller.robot.v1;

import com.ai.domain.bo.UserConfig;
import com.ai.domain.vo.Message;
import com.ai.service.UserConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController("RobotConfigController")
@RequestMapping("/robot/api/v1")
public class UserConfigController {

    @Autowired
    private UserConfigService userConfigService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ResponseBody
    @GetMapping("/user/{userId}/config/{name}")
    public Message getUserConfig(@PathVariable String userId, @PathVariable String name){
        UserConfig userConfig =  userConfigService.getConfigByUserId(userId,name);
        if(userConfig!=null){
            return new Message().ok(0, "success").addData("config",userConfig);
        } else {
            return new Message().error(1, "查询失败");
        }
    }
}
