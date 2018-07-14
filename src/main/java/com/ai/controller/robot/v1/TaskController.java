package com.ai.controller.robot.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import com.ai.service.GatewayService;
import com.ai.service.TaskService;
import com.ai.service.TaskUserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController("RobotTaskController")
@RequestMapping("/robot/api/v1")
public class TaskController {

    @Autowired
    private TaskUserService taskUserService;

    @ApiOperation(value = "分页获取taskUser", notes = "根据id查询taskUser信息")
    @ResponseBody
    @GetMapping("task/users")
    public Message findTaskUserById( int id){
        if(id<0){
            return new Message().error(3107, "缺少参数 id");
        }
        List<TaskUser> taskUserList = taskUserService.selectTaskUserByTaskId(id);

        if(taskUserList.size()>0){
            return new Message().ok(3103, "查询成功").addData("taskUserList",taskUserList);
        } else {
            return new Message().error(3104, "查询失败");
        }
    }

}
