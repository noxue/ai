package com.ai.controller.robot.v1;

import com.ai.domain.bo.*;
import com.ai.domain.vo.Message;
import com.ai.service.SimService;
import com.ai.service.TaskService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("RobotSimController")
@RequestMapping("/robot/api/v1")
public class SimController {

    @Autowired
    private SimService simService;

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "sim卡信息", notes = "根据网关信息查询sim信息")
    @ResponseBody
    @GetMapping("gateway/{id}/sims")
    public Message findGatewaysByAppId(@PathVariable int id){

        if(id<=0){
            return new Message().error(3107, "参数id不合法");
        }
        PageInfo<Sim> SimList = simService.findSimByGatewayId(1,1000,id);
        if(SimList!=null){
            return new Message().ok(0, "success").addData("sims",SimList.getList());
        } else {
            return new Message().error(3104, "查询失败");

        }
    }

    @ApiOperation(value = "simUser信息", notes = "根据simid信息查询simUser信息")
    @ResponseBody
    @GetMapping("/sim/{sim_id}/tasks")
    public Message findTaskBySimId(@PathVariable int sim_id){
        if(sim_id<=0){
            return new Message().error(1, "缺少参数 id");
        }
        PageInfo<SimUser> SimUserList = simService.findSimUserBySimId(1,1000,sim_id+"");
        if(SimUserList == null){
            return new Message().error(2, "查询失败");
        }

        List<String> ids = new ArrayList<>();

        List<SimUser> users = SimUserList.getList();
        for (SimUser user : users) {
            ids.add(user.getUserId());
        }
        Sim sim = simService.getSimById(sim_id);
        if (sim != null) {
            ids.add(sim.getUserId());
        }
        //根据userId获取task集合
        List<Task> tasks =taskService.getStartedTasksByUserId(ids);
        if(tasks.size()>0){
            for (Task t : tasks) {
                t.setStatus((byte)3);
                taskService.editTask(t);
            }
            return new Message().ok(0, "success").addData("tasks",tasks);
        } else {
            return new Message().error(3, "没有准备好的任务");
        }
    }
}
