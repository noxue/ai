package com.ai.controller.web.v1;

import com.ai.domain.bo.Template;
import com.ai.domain.vo.Message;
import com.ai.service.AccountService;
import com.ai.service.TemplateService;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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


    @Autowired
    private TemplateService templateService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @ApiOperation(value = "新增Template", notes = "增加一个Template模板信息")
    @ResponseBody
    @PostMapping("")
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
        template.setUserId(appId);
        template.setName(name);
        template.setContent(content);
        template.setCreatedAt(new Date());
        if (templateService.registerTemplate(template)) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(4403, "新增失败");
        }
    }

    @ApiOperation(value = "编辑Template", notes = "Template")
    @ResponseBody
    @PutMapping("/{id}")
    public Message editTemplate(HttpServletRequest request, @PathVariable long id){
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        Template template = new Template();
        String content = params.get("content");
        String name = params.get("name");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(appId) || StringUtils.isEmpty(content)|| !(id>0)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4404, "信息不全");
        }
        template.setId(id);
        template.setUserId(appId);
        template.setName(name);
        template.setContent(content);
        //修改
        if (templateService.editTemplate(template)) {
            redisTemplate.opsForValue().set("template_"+ id, "edit");
            return new Message().ok(0, "success");
        } else {
            return new Message().error(4406, "编辑失败");
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
            redisTemplate.opsForValue().set("template_"+ id, "del");
            return new Message().ok(0, "success");
        } else {
            return new Message().error(4409, "删除失败");
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
        //根据appId获取角色权限列表
        String appId =request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String name = params.get("name");
        pageNum = Integer.parseInt(params.get("page"));
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        String roleId= accountService.loadAccountRoleId(appId);
        if(roleId.equals("100")){
            appId = "";
        }
        PageInfo<Template> templatePage = templateService.findAllTemlate(pageNum,pageSize,appId ,name);
        //分页获取模板信息
        if(templatePage!=null){
            return new Message().ok(0, "success").addData("templateList",templatePage);
        } else {
            return new Message().error(4411, "查询失败");
        }
    }

    @ApiOperation(value = "分页获取template", notes = "模糊查询分页获取template信息")
    @ResponseBody
    @PostMapping("/listById")
    public Object findTemplateListlistById(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                          int pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "1000")
                                          int pageSize){
        //根据appId获取角色权限列表
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        //判断当前操作的用户的权限
        String roleId= accountService.loadAccountRoleId(appId);
        if(roleId.equals("100")){
            appId = "";
        }
        PageInfo<Template> templatePage = templateService.findAllTemlate(pageNum,pageSize,appId ,"");
        //分页获取模板信息
        if(templatePage!=null){
            return new Message().ok(0, "success").addData("templateList",templatePage);
        } else {
            return new Message().error(4411, "查询失败");
        }
    }
     /*
     * @Description 根据id获取template信息
     * @Param [] id
     * @Return com.ai.domain.bo.template.java
     */
    @ApiOperation(value = "获取template", notes = "根据id获取template信息")
    @ResponseBody
    @GetMapping("/{id}")
    public Message getTplById(@PathVariable int id){
        if (!(id>0)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4407, "id值不合法");
        }
        Template template = templateService.getTemplateById(id);
        if (template !=null){
            return new Message().ok(0, "success").addData("template",template);
        } else {
            return new Message().error(4413, "查询失败");
        }
    }

}
