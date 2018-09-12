package com.ai.controller.robot.v1;

import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.vo.Message;
import com.ai.service.TaskService;
import com.ai.service.TaskUserService;
import com.ai.service.wx.WechatService;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @Autowired
    private WechatService wechatService;

    @ApiOperation(value = "分页获取taskUser", notes = "根据id查询taskUser信息")
    @ResponseBody
    @GetMapping("task/{id}/users")
    public Message findTaskUserById( @PathVariable("id") long taskId){
        if(taskId<0){
            return new Message().error(1, "缺少参数 id");
        }
        synchronized(this) {
            Task task = taskService.getTaskById(taskId);

            // 如果是未开始的任务，就不要获取
            if (task.getStatus() != 3 ){
                return new Message().error(2, "任务未开始");
            } else if (task.getStatus() == 0) {
                return new Message().error(3, "任务已结束");
            } else if (task.getStatus() == 4) {
                return new Message().error(4, "任务已暂停");
            }

            List<TaskUser> taskUserList = taskUserService.getTaskUserAndUpdate(taskId);

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
                return new Message().error(5, "没有客户信息");
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

        TaskUser taskUser = taskUserService.getTaskUserById(userId);
        if (taskUser == null) {
            return new Message().ok(0, "success");
        }
        taskUser.setId(userId);
        taskUser.setStatus((byte)0);
        taskUser.setCalledAt(new Date());
        taskUser.setTime(time);
        taskUser.setType((byte)type);
        taskUser.setContent(report);

        if(taskUserService.editTaskUser(taskUser)){
            Task task = taskService.getTaskById(taskUser.getTaskId());
            if (task == null) {
                return new Message().ok(0, "success");
            }
            String follows = task.getFollow();
            if(StringUtils.isEmpty(follows)){
                return new Message().ok(0, "success");
            }else{
                try {
                    JSONObject jsStr = JSONObject.parseObject(report);
                    if (jsStr==null) {
                        return new Message().ok(0, "success");
                    }
                    String[] follow = follows.split(",");
                    List<String> atten = new ArrayList<>();
                    String userType = jsStr.get("type") + "";
                    for (int i = 0, len = follow.length; i < len; i++) {
                        if (follow[i].equals(userType)) {
                            atten.add(task.getName());
                            atten.add(taskUser.getMobile());
                            atten.add(replaceType(follow[i]));
                            atten.add(taskUser.getId().toString());
                        }
                    }

                    if (atten.size() > 0) {
                        List<String> openids = wechatService.getOpenid(task.getUserId());
                        if (openids != null) {
                            for (int i = 0; i < openids.size(); i++) {
                                wechatService.senMsg(openids.get(i), atten);
                            }
                        }
                    }
                }catch (JSONException ex) {
                    return new Message().ok(0, "success");
                }
            }
            return new Message().ok(0, "success");
        } else {
            return new Message().error(2, "添加失败");
        }
    }

    public String replaceType(String type){
        String retype = "";
        if("5".equals(type)){
            retype = "A类";
        }else if("6".equals(type)){
            retype = "B类";
        }else if("7".equals(type)){
            retype = "C类";
        }else if("8".equals(type)){
            retype = "D类";
        }else if("9".equals(type)){
            retype = "E类";
        }else if("10".equals(type)){
            retype = "F类";
        }
        return retype;
    }
}
