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
    public Message findTaskUserById( @PathVariable long id){
        if(id<0){
            return new Message().error(5021, "缺少参数 id");
        }
        List<TaskUser> taskUserList = taskUserService.getTaskUserAndUpdate(id);

        if(taskUserList.size()>0){
            return new Message().ok(0, "success").addData("users",taskUserList);
        } else {
            return new Message().error(3104, "查询失败");
        }
    }

}
