package com.ai.controller.robot.v1;

import com.ai.domain.bo.Template;
import com.ai.domain.vo.Message;
import com.ai.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("RobotTemplateController")
@RequestMapping("/robot/api/v1")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("tpl/{id}")
    @ResponseBody
    public Message getTpl(@PathVariable long id){
        Message msg = new Message();
        if (id <= 0 ){
            return msg.error(4001,"invalid id param");
        }
        Template template = templateService.getTemplateById(id);
        if (template == null) {
            return msg.error(4002,"template not found");
        }
        return msg.ok(0,"success").addData("tpl",template);
    }
}
