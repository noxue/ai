package com.ai.controller.web.v1;

import com.ai.domain.bo.App;
import com.ai.domain.vo.Message;
import com.ai.service.AppService;
import com.ai.util.*;
import com.github.pagehelper.PageInfo;
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
public class AppController extends BasicAction {

    @Autowired
    private AppService appService;

    @ApiOperation(value = "新增app", notes = "增加一个新的,name不可重复的app")
    @ResponseBody
    @PostMapping("/add")
    public Message appRegister(HttpServletRequest request, HttpServletResponse response) {

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
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3004, "新增失败");
        }
    }

    @ApiOperation(value = "编辑app", notes = "修改app信息，修改后的name不可重复")
    @ResponseBody
    @PostMapping("/edit")
    public Message editApp(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        App app = new App();
        String id = params.get("id");
        String key = params.get("key");
        String name = params.get("name");
        String description = params.get("description");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(key) || StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3005, "app信息缺失");
        }
        App oldApp = appService.getAppById(Long.parseLong(id));
        if(!oldApp.getName().equals(name)){
            if (appService.isAppExistByName(name, id)) {
                return new Message().error(3002, "名称已被占用");
            }
        }
        if(key.length()!=16){
            return new Message().error(3005, "请确保key的长度为16");
        }
        app.setId(Long.parseLong(id.trim()));
        app.setName(name);
        app.setKey(key);
        app.setDescription(description);
        if (appService.editApp(app)) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3007, "编辑失败");
        }
    }

    @ApiOperation(value = "删除app", notes = "删除app信息")
    @ResponseBody
    @PostMapping("/del")
    public Message delApp(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id = params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3012, "app信息缺失");
        }

        if (appService.delApp(Long.parseLong(id))) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3009, "删除失败");
        }
    }

    /* *
     * @Description 分页获取所有app
     * @Param [] name
     * @Return com.ai.domain.bo.app.java
     */
    @ApiOperation(value = "分页获取app", notes = "模糊查询分页获取app信息，用于页面展示")
    @ResponseBody
    @PostMapping("/all")
    public Object findAllApp(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                     int pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                                     int pageSize) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String name = params.get("name");
        pageNum = Integer.parseInt(params.get("page"));
        PageInfo<App> appList = appService.findAllApp(pageNum, pageSize, name);
        if ( appList!= null) {
            return new Message().ok(0, "success").addData("appList", appList);
        } else {
            return new Message().error(3014, "查询失败");
        }
    }

    /* *
     * @Description 获取所有app
     * @Param []
     * @Return com.ai.domain.bo.app.java
     */
    @ApiOperation(value = "获取所有app信息", notes = "用于下拉框选择")
    @ResponseBody
    @PostMapping("/allApp")
    public Object findAll(HttpServletRequest request, HttpServletResponse response) {
        if (appService.findAllApp(1, 111111111, "") != null) {
            return new Message().ok(0, "success").addData("appList", appService.findAllApp(1, 111111111, ""));
        } else {
            return new Message().error(3014, "查询失败");
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
    public Object selectAppById(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id = params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3012, "app信息缺失");
        }
        App app = appService.getAppById(Long.parseLong(id));
        if (app != null) {
            return new Message().ok(0, "success").addData("app", app);
        } else {
            return new Message().error(3011, "查询失败");
        }
    }

}
