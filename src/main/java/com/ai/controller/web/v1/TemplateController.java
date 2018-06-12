package com.ai.controller.web.v1;

import com.ai.domain.bo.Template;
import com.ai.domain.vo.Message;
import com.ai.service.TemplateService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,patch部分更新,del删除
 * 2018年6月11日15:04:39
 */
@RestController
@RequestMapping("/web/api/v1/template")
public class TemplateController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private TemplateService templateService;

    @ApiOperation(value = "新增Template", notes = "增加一个Template模板信息")
    @ResponseBody
    @PostMapping("/add")
    public Message addTemplate(HttpServletRequest request, HttpServletResponse response){

        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Template template = new Template();
        String content = params.get("content");
        String name = params.get("name");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(appId) || StringUtils.isEmpty(content)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4400, "信息不全");
        }
        if(templateService.isTemplateExistByName(name)){
            return new Message().error(4401, "该名称已被占用");
        }

        template.setUserId(appId);
        template.setStatus((byte) 3);
        template.setName(name);
        template.setContent(content);
        template.setCreatedAt(new Date());

        if (templateService.registerTemplate(template)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/add", "addTemplate", (short) 4402, "新增成功"));
            return new Message().ok(4402, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/add", "addTemplate", (short) 4403, "新增失败"));
            return new Message().ok(4403, "新增失败");
        }
    }

    @ApiOperation(value = "编辑Template", notes = "Template")
    @ResponseBody
    @PostMapping("/edit")
    public Message editTemplate(HttpServletRequest request, HttpServletResponse response){
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Template template = new Template();
        String id = params.get("id");
        String content = params.get("content");
        String name = params.get("name");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(appId) || StringUtils.isEmpty(content)|| StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4404, "信息不全");
        }
        if(templateService.isTemplateExistByName(name)){
            return new Message().error(4401, "该名称已被占用");
        }

        template.setId(Long.parseLong(id));
        template.setUserId(appId);
        template.setStatus((byte) 3);
        template.setName(name);
        template.setContent(content);
        //修改
        if (templateService.editTemplate(template)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/edit", "editTemplate", (short) 4404, "编辑成功"));
            return new Message().ok(4405, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/edit", "editTemplate", (short) 4405, "编辑失败"));
            return new Message().ok(4406, "编辑失败");
        }
    }

    @ApiOperation(value = "删除template", notes = "删除template信息")
    @ResponseBody
    @PostMapping("/del")
    public Message delTemplate(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4407, "信息不全");
        }

        if (templateService.delTemplate(Long.parseLong(id))) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/del", "delTemplate", (short) 4408, "删除成功"));
            return new Message().ok(4408, "删除成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/del", "delTemplate", (short) 4409, "删除失败"));
            return new Message().ok(4409, "删除失败");
        }
    }

     /*
     * @Description 分页获取当前用户的所有template
     * @Param [] userid
     * @Return com.ai.domain.bo.template.java
     */
    @ApiOperation(value = "分页获取template", notes = "模糊查询分页获取template信息")
    @ResponseBody
    @PostMapping("/all")
    public Object findAllTemplate(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                    int pageSize){
        //根据userid获取角色权限列表
        String appId =request.getHeader("appId");
        //分页获取模板信息
        if(templateService.findAllTemlate(pageNum,pageSize,appId)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/templateList/all", "findAllTemplate", (short) 4408, "查询成功"));
            return new Message().ok(4410, "查询成功").addData("templateList",templateService.findAllTemlate(pageNum,pageSize,appId));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/templateList/all", "findAllTemplate", (short) 4409, "查询失败"));
            return new Message().ok(4411, "查询失败");
        }
    }

     /*
     * @Description 根据id获取template信息
     * @Param [] id
     * @Return com.ai.domain.bo.template.java
     */
    @ApiOperation(value = "获取template", notes = "根据id获取template信息")
    @ResponseBody
    @PostMapping("/select")
    public Object selectTemplateById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4407, "信息不全");
        }
        Template template = templateService.getTemplateById(Long.parseLong(id));
        if (template !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/select", "selectTemplateById", (short) 4412, "查询成功"));
            return new Message().ok(4412, "查询成功").addData("template",template);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/template/select", "selectTemplateById", (short) 4413, "查询失败"));
            return new Message().ok(4413, "查询失败");
        }
    }

}
