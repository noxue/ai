package com.ai.controller.web.v1;

import com.ai.domain.bo.UserConfig;
import com.ai.domain.vo.Message;
import com.ai.service.UserConfigService;
import com.ai.util.RequestResponseUtil;
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


@RestController
@RequestMapping("/web/api/v1/config")
public class UserConfigController extends BasicAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConfigController.class);

    @Autowired
    private UserConfigService userConfigService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "添加用户配置", notes = "添加用户配置信息")
    @ResponseBody
    @PostMapping("/add")
    public Message appRegister(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String rep= params.get("rep");
        String repeat = "{\"repeat\" : ["+ rep +"],";
        String sech = params.get("sech");
        String schedule = " \"schedule\" : " + sech + "}";
        if (StringUtils.isEmpty(userId)) {
            // 必须信息缺一不可,返回请检查用户名
            return new Message().error(4004, "当前用户未登录");
        }
        UserConfig oldConfig = userConfigService.getConfigByUserId(userId, "schedule");

        if (oldConfig ==null) {
            //执行新增操作
            UserConfig config = new UserConfig();
            config.setUserId(userId);
            config.setKey("schedule");
            config.setValue(repeat + schedule);
            if (userConfigService.insertConfig(config)) {
                return new Message().ok(0, "success");
            } else {
                return new Message().error(7000, "新增失败");
            }
        } else {
            //执行更新操作
            oldConfig.setValue(repeat + schedule);
            if (userConfigService.editConfig(oldConfig)) {
                redisTemplate.opsForValue().set("config_"+ oldConfig.getUserId(), "edit");
                return new Message().ok(0, "success");
            } else {
                return new Message().error(7001, "修改失败");
            }

        }
    }

  @ApiOperation(value = "获取用户配置", notes = "根据userid获取userConfig")
    @ResponseBody
    @PostMapping("/select")
    public Object selectUserConfigByUserId(HttpServletRequest request, HttpServletResponse response){
        String appId = request.getHeader("appId");
        if ( StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        UserConfig config = userConfigService.getConfigByUserId(appId, "schedule");

        if (config !=null){
            return new Message().ok(0, "success").addData("userConfig",config);
        } else {
            return new Message().error(7002, "查询失败");
        }
    }

}
