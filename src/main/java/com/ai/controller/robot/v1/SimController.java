package com.ai.controller.robot.v1;

import com.ai.domain.bo.*;
import com.ai.domain.vo.Message;
import com.ai.service.SimService;
import com.ai.service.TaskService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("RobotSimController")
@RequestMapping("/robot/api/v1/sim")
public class SimController {

    @Autowired
    private SimService simService;

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "sim卡信息", notes = "根据网关信息查询sim信息")
    @ResponseBody
    @GetMapping("/all")
    public Message findGatewaysByAppId(int id,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                              int pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "1500")
                                              int pageSize){

        if(id<0){
            return new Message().error(3107, "缺少参数 id");
        }
        PageInfo<Sim> SimList = simService.findSimByGatewayId(pageNum,pageSize,id);
        if(SimList!=null){
            return new Message().ok(0, "success").addData("gatewayList",SimList.getList());
        } else {
            return new Message().error(8003, "查询失败");
        }
    }

    @ApiOperation(value = "simUser信息", notes = "根据simid信息查询simUser信息")
    @ResponseBody
    @GetMapping("/tasks")
    public Message findTaskBySimId(int id,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                              int pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "1500")
                                              int pageSize){

        if(id<0){
            return new Message().error(4006, "缺少参数 id");
        }
        PageInfo<SimUser> SimUserList = simService.findSimUserBySimId(pageNum,pageSize,id+"");
        if(SimUserList==null){
            return new Message().error(8008, "查询失败");
        }
        if(SimUserList.getList().size()==0){
            return new Message().error(8009, "该卡当前未绑定用户");
        }
        //根据userId获取task集合
        List<Task> taskList =taskService.findTaskByUserId(SimUserList.getList());
        if(taskList.size()>0){
            return new Message().ok(0, "success").addData("taskList",taskList);
        } else {
            return new Message().error(8004, "查询失败");
        }
    }
}
