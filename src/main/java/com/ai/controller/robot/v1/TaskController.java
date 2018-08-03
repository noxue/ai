package com.ai.controller.robot.v1;

import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import com.ai.service.TaskService;
import com.ai.service.TaskUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController("RobotTaskController")
@RequestMapping("/robot/api/v1")
public class TaskController {

    @Autowired
    private TaskUserService taskUserService;


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
            Task task = taskService.getTaskById(taskId);

            if (taskUserList.size()>0) {
                task.setCalled(task.getCalled() + taskUserList.size());
            } else {
                task.setFinishAt(new Date());
                task.setStatus((byte)0);
            }
            taskService.editTask(task);
            if (taskUserList.size() > 0) {
                return new Message().ok(0, "success").addData("users", new HashSet<>(taskUserList));
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
    public Message report(HttpServletRequest request, @PathVariable("id") long userId,  String report, int time, int type){
        if(userId<0){
            return new Message().error(1, "缺少参数 id");
        }

        TaskUser taskUser = new TaskUser();
        taskUser.setId(userId);
        taskUser.setContent(report);
        taskUser.setStatus((byte)0);
        taskUser.setCalledAt(new Date());
        taskUser.setTime(time);
        taskUser.setType((byte)type);

        if(taskUserService.editTaskUser(taskUser)){
            return new Message().ok(0, "success");
        } else {
            return new Message().error(2, "添加失败");
        }
    }
}
