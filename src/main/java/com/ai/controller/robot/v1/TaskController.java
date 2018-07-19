package com.ai.controller.robot.v1;

import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import com.ai.service.TaskUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("RobotTaskController")
@RequestMapping("/robot/api/v1")
public class TaskController {

    @Autowired
    private TaskUserService taskUserService;

    @ApiOperation(value = "分页获取taskUser", notes = "根据id查询taskUser信息")
    @ResponseBody
    @GetMapping("task/{id}/users")
    public Message findTaskUserById( @PathVariable("id") long taskId){
        if(taskId<0){
            return new Message().error(1, "缺少参数 id");
        }
        List<TaskUser> taskUserList = taskUserService.getTaskUserAndUpdate(taskId);

        if(taskUserList.size()>0){
            return new Message().ok(0, "success").addData("users",taskUserList);
        } else {
            return new Message().error(2, "没有客户信息");
        }
    }

}
