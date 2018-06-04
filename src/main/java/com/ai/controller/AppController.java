package com.ai.controller;

import com.ai.domain.bo.App;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.vo.Message;
import com.ai.service.impl.AppServiceImpl;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.util.StringUtils;

/* *
 * @Author ws
 * @Description post新增,get读取,put完整更新,patch部分更新,delete删除
 * @2018年6月4日9:55:36
 */
@RestController
@RequestMapping("/app")
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppServiceImpl appService;

    /* *
     * @Description 分页获取所有app
     * @Param [] null
     * @Return com.ai.domain.bo.app.java
     */
    @ApiOperation(value = "获取app", notes = "分页获取所有app")
    @ResponseBody
    @GetMapping("/all")
    public Object findAllApp(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                    int pageSize){
        return appService.findAllApp(pageNum,pageSize);
    }

    @ApiOperation(value = "新增app", notes = "增加一个新的、name不可重复的app，")
    @ResponseBody
    @PostMapping("/registerApp")
    public Message appRegister(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        App app = new App();
        String name = params.get("name");
        String description = params.get("description");
        if (StringUtils.isEmpty(name)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(3001, "请检查app的名称");
        }
        if (appService.isAppExistByName(name)) {
            // name已存在
            return new Message().error(3002, "名称已存在");
        }
        String key = Math.random()+"";
        app.setKey(key.substring(2,18));
        app.setDescription(description);
        app.setName(name);

        if (appService.registerApp(app)) {
            //LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(name, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
            return new Message().ok(3003, "新增成功");
        } else {
            //LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(name, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return new Message().ok(3004, "新增失败");
        }
    }
}
