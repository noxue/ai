package com.ai.controller.web.v1;

import com.ai.domain.bo.App;
import com.ai.domain.vo.Message;
import com.ai.service.AppService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.util.StringUtils;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,patch部分更新,del删除
 * @2018年6月4日9:55:36
 */
@RestController
@RequestMapping("/web/api/v1/app")
public class AppController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    @ApiOperation(value = "新增app", notes = "增加一个新的,name不可重复的app")
    @ResponseBody
    @PostMapping("/add")
    public Message appRegister(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        App app = new App();
        String name = params.get("name");
        String description = params.get("description");
        if (StringUtils.isEmpty(name)) {
            // 必须信息缺一不可,返回请检查用户名
            return new Message().error(3001, "请检查用户名");
        }
        if (appService.isAppExistByName(name)) {
            // name已存在
            return new Message().error(3002, "名称已被占用");
        }
        app.setKey(RandomUtil.getRandomString(16));
        app.setDescription(description);
        app.setName(name);

        if (appService.registerApp(app)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/add", "registerApp", (short) 3003, "新增成功"));
            return new Message().ok(3003, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/add", "registerApp", (short) 3004, "新增失败"));
            return new Message().ok(3004, "新增失败");
        }
    }

    @ApiOperation(value = "编辑app", notes = "修改app信息，修改后的name不可重复")
    @ResponseBody
    @PostMapping("/edit")
    public Message editApp(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        App app =  new App();
        String id =params.get("id");
        String key =params.get("key");
        String name =params.get("name");
        String description =params.get("description");
        if (StringUtils.isEmpty(name)|| StringUtils.isEmpty(key)|| StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3005, "app信息缺失");
        }
        if (appService.isAppExistByName(name)) {
            // name已存在
            return new Message().error(3002, "名称已被占用");
        }
        app.setId(Long.parseLong(id));
        app.setKey(key);
        app.setDescription(description);
        app.setName(name);
        if (appService.editApp(app)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/edit", "editApp", (short) 3006, "编辑成功"));
            return new Message().ok(3006, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/edit", "editApp", (short) 3007, "编辑失败"));
            return new Message().ok(3007, "编辑失败");
        }
    }

    @ApiOperation(value = "删除app", notes = "删除app信息")
    @ResponseBody
    @PostMapping("/del")
    public Message delApp(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3012, "app信息缺失");
        }

        if (appService.delApp(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/del", "delApp", (short) 3008, "删除成功"));
            return new Message().ok(3008, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/del", "delApp", (short) 3009, "删除失败"));
            return new Message().ok(3009, "删除失败");
        }
    }

    /* *
     * @Description 分页获取所有app
     * @Param [] name
     * @Return com.ai.domain.bo.app.java
     */
    @ApiOperation(value = "分页获取app", notes = "模糊查询分页获取app信息")
    @ResponseBody
    @PostMapping("/all")
    public Object findAllApp(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                    int pageSize){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String name =params.get("name");
        if(appService.findAllApp(pageNum,pageSize,name)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/all", "findAllApp", (short) 3010, "查询成功"));
            return new Message().ok(3003, "查询成功").addData("appList",appService.findAllApp(pageNum,pageSize,name));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/all", "findAllApp", (short) 3011, "查询失败"));
            return new Message().ok(3004, "查询失败");
        }
    }

    /* *
     * @Description 根据id获取app信息
     * @Param [] id
     * @Return com.ai.domain.bo.app.java
     */
    @ApiOperation(value = "获取app", notes = "根据id获取app信息")
    @ResponseBody
    @PostMapping("/select")
    public Object selectAppById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3012, "app信息缺失");
        }
        App app = appService.getAppById(Long.parseLong(id));
        if (app !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/select", "selectAppById", (short) 3010, "查询成功"));
            return new Message().ok(3010, "查询成功").addData("app",app);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/select", "selectAppById", (short) 3011, "查询失败"));
            return new Message().ok(3011, "查询失败");
        }
    }

}
