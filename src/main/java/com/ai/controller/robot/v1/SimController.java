package com.ai.controller.robot.v1;

import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimUser;
import com.ai.domain.bo.Task;
import com.ai.domain.vo.Message;
import com.ai.service.GatewayService;
import com.ai.service.SimService;
import com.ai.service.TaskService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/ByGatewayId", "findGatewaysByAppId", (short) 3110, "查询成功"));
            return new Message().ok(3103, "查询成功").addData("gatewayList",SimList.getList());
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/ByGatewayId", "findGatewaysByAppId", (short) 3111, "查询失败"));
            return new Message().error(3104, "查询失败");
        }
    }

    @ApiOperation(value = "simUser信息", notes = "根据simid信息查询simUser信息")
    @ResponseBody
    @GetMapping("/tasks")
    public Message findSimUserBySimId(int id,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                              int pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "1500")
                                              int pageSize){

        if(id<0){
            return new Message().error(3107, "缺少参数 id");
        }
        PageInfo<SimUser> SimUserList = simService.findSimUserBySimId(pageNum,pageSize,id+"");
        if(SimUserList==null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/BySimId", "findSimUserBySimId", (short) 3111, "查询失败"));
            return new Message().error(3104, "查询失败");
        }
        if(SimUserList.getList().size()==0){
            return new Message().error(3104, "该卡当前未绑定用户");
        }
        //根据userId获取task集合
        List<Task> taskList =taskService.findTaskByUserId(SimUserList.getList());
        if(taskList.size()>0){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/BySimId", "findSimUserBySimId", (short) 3110, "查询成功"));
            return new Message().ok(3103, "查询成功").addData("taskList",taskList);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/sim/BySimId", "findSimUserBySimId", (short) 3111, "查询失败"));
            return new Message().error(3104, "查询失败");
        }
    }
}
