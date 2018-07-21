package com.ai.controller.robot.v1;

import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.bo.TaskUserReport;
import com.ai.domain.vo.Message;
import com.ai.service.TaskService;
import com.ai.service.TaskUserReportService;
import com.ai.service.TaskUserService;
import com.ai.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController("RobotTaskController")
@RequestMapping("/robot/api/v1")
public class TaskController {

    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private TaskUserReportService taskUserReportService;

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "分页获取taskUser", notes = "根据id查询taskUser信息")
    @ResponseBody
    @GetMapping("task/{id}/users")
    public Message findTaskUserById( @PathVariable("id") long taskId){
        if(taskId<0){
            return new Message().error(1, "缺少参数 id");
        }
        synchronized(this) {
            List<TaskUser> taskUserList = taskUserService.getTaskUserAndUpdate(taskId);

            if (taskUserList.size() > 0) {
                return new Message().ok(0, "success").addData("users", taskUserList);
            } else {
                return new Message().error(2, "没有客户信息");
            }
        }
    }

    @ApiOperation(value = "根据任务id获取任务", notes = "根据任务id获取任务")
    @ResponseBody
    @GetMapping("task/{id}")
    public Message findTaskById( @PathVariable("id") long taskId){
        if(taskId<0){
            return new Message().error(1, "缺少参数 id");
        }

        Task task = taskService.getTaskById(taskId);

        if(task!=null){
            Task[] ts = {task};
            return new Message().ok(0, "success").addData("tasks",ts);
        } else {
            return new Message().error(2, "没有客户信息");
        }
    }

    @ApiOperation(value = "提交报告信息", notes = "根据任务用户")
    @ResponseBody
    @PostMapping("task/user/{id}/report")
    public Message report(HttpServletRequest request, @PathVariable("id") long userId, String report){
        if(userId<0){
            return new Message().error(1, "缺少参数 id");
        }


        TaskUserReport taskUserReport =  new TaskUserReport();
        taskUserReport.setTaskUserId(userId);
        taskUserReport.setContent(report);



        if(taskUserReportService.addTaskUserReport(taskUserReport)){
            return new Message().ok(0, "success");
        } else {
            return new Message().error(2, "添加失败");
        }
    }
}
