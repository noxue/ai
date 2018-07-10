package com.ai.controller.web.v1;

import com.ai.domain.bo.App;
import com.ai.domain.bo.UserConfig;
import com.ai.domain.vo.Message;
import com.ai.service.UserConfigService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
            return new Message().error(3001, "请重新登陆");
        }
        UserConfig oldConfig = userConfigService.getConfigByUserId(userId, "schedule");

        if (oldConfig ==null) {
            //执行新增操作
            UserConfig config = new UserConfig();
            config.setUserId(userId);
            config.setKey("schedule");
            config.setValue(repeat + schedule);
            if (userConfigService.insertConfig(config)) {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog("admin", "/app/add", "registerApp", (short) 3003, "新增成功"));
                return new Message().ok(3003, "新增成功");
            } else {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog("admin", "/app/add", "registerApp", (short) 3004, "新增失败"));
                return new Message().error(3004, "新增失败");
            }
        } else {
            //执行更新操作
            oldConfig.setValue(repeat + schedule);
            if (userConfigService.editConfig(oldConfig)) {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog("admin", "/config/edit", "registerApp", (short) 3003, "新增成功"));
                return new Message().ok(3003, "修改成功");
            } else {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog("admin", "/config/edit", "registerApp", (short) 3004, "新增失败"));
                return new Message().error(3004, "修改失败");
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
            return new Message().error(3012, "缺少授权信息");
        }
        UserConfig config = userConfigService.getConfigByUserId(appId, "schedule");

        if (config !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/config/select", "selectUserConfigByUserId", (short) 3010, "查询成功"));
            return new Message().ok(3010, "查询成功").addData("userConfig",config);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/config/select", "selectUserConfigByUserId", (short) 3011, "查询失败"));
            return new Message().error(3011, "查询失败");
        }
    }

}
