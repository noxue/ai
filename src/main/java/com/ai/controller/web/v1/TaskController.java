package com.ai.controller.web.v1;

import com.ai.domain.bo.AuthRole;
import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.bo.TaskUserReport;
import com.ai.domain.vo.Message;
import com.ai.service.*;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,patch部分更新,del删除
 * @2018年6月9日17:50:34
 */
@RestController
@RequestMapping("/web/api/v1/task")
public class TaskController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private TaskUserReportService taskUserReportService;

    @Autowired
    private SimService simService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增taskUserReport", notes = "添加taskUserReport信息")
    @ResponseBody
    @PostMapping("/user/report/add")
    public Message addTaskUserReport(HttpServletRequest request, HttpServletResponse response){

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        TaskUserReport taskUserReport = new TaskUserReport();
        String task_user_id = params.get("task_user_id");
        String content = params.get("content");
        if (StringUtils.isEmpty(task_user_id) ||StringUtils.isEmpty(content)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(5000, "信息不全");
        }
        taskUserReport.setTaskUserId(Long.parseLong(task_user_id));
        taskUserReport.setContent(content);

        if (taskUserReportService.addTaskUserReport(taskUserReport)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/add", "registerApp", (short) 3003, "新增成功"));
            return new Message().ok(5001, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/app/add", "registerApp", (short) 3004, "新增失败"));
            return new Message().error(5002, "新增失败");
        }
    }

    /* *
     * @Description 根据id获取TaskUserReport信息
     * @Param [] id
     * @Return com.ai.domain.bo.TaskUserReport.java
     */
    @ApiOperation(value = "获取TaskUserReport", notes = "根据id获取TaskUserReport信息")
    @ResponseBody
    @PostMapping("/user/report/select")
    public Object selectTaskUserReportById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5004, "信息缺失");
        }
        TaskUserReport taskUserReport = taskUserReportService.getTaskUserReportById(Long.parseLong(id));
        if (taskUserReport !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/report/select", "getTaskUserReportById", (short) 5002, "查询成功"));
            return new Message().ok(5005, "查询成功").addData("taskUserReport",taskUserReport);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/report/select", "getTaskUserReportById", (short) 5003, "查询失败"));
            return new Message().error(5006, "查询失败");
        }
    }


    @ApiOperation(value = "新增task", notes = "添加task信息")
    @ResponseBody
    @PostMapping("/add")
    public Message addTask(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String user_id =  request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String sim_id = params.get("sim_id");
        //验证当前用户是否有可用的卡
        if(simService.isExistInSim(user_id,sim_id)){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "", "isExistInSim", (short) 5019, "验证成功"));
        }else {
            if(simService.isExistInSimUser(user_id,sim_id)){
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "", "isExistInSimUser", (short) 5020, "验证成功"));
            }else{
                return new Message().error(5018, "当前用户没有可用的卡");
            }
        }
        String template_id = params.get("template_id");
        String thread = params.get("thread");
        String test = params.get("test");
        // 必须信息缺一不可,返回验证消息
        if (StringUtils.isEmpty(user_id) ||StringUtils.isEmpty(template_id)) {
            return new Message().error(5007, "信息不全");
        }

        Task task = new Task();
        task.setUserId(user_id);
        if(StringUtils.isEmpty(thread)){
            task.setThread(Integer.parseInt(thread));
        }
        task.setTemplateId(Long.parseLong(template_id));
        task.setCreatedAt(new Date());
        if(test.equals("1")){
            task.setTest(true);
        }
        if (taskService.registerTask(task)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/add", "addTask", (short) 3003, "新增成功"));
            return new Message().ok(5008, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/add", "addTask", (short) 3004, "新增失败"));
            return new Message().error(5009, "新增失败");
        }
    }

    @ApiOperation(value = "新增TaskUser", notes = "添加TaskUser信息")
    @ResponseBody
    @PostMapping("/user/add")
    public Message addTaskUser(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String mobile = params.get("mobile");
        String task_id = params.get("task_id");
        // 必须信息缺一不可,返回验证消息
        if (StringUtils.isEmpty(mobile) ||StringUtils.isEmpty(task_id) ) {
            return new Message().error(5010, "信息不全");
        }
        TaskUser taskUser = new TaskUser();
        taskUser.setMobile(mobile);
        taskUser.setTaskId(Long.parseLong(task_id));

        if (taskUserService.addTaskUser(taskUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/add", "addTaskUser", (short) 5011, "新增成功"));
            return new Message().ok(5011, "新增成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/add", "addTaskUser", (short) 5012, "新增失败"));
            return new Message().error(5012, "新增失败");
        }
    }

    @ApiOperation(value = "编辑TaskUser", notes = "修改TaskUser，主要用于公开所有客户资料")
    @ResponseBody
    @PostMapping("/user/share")
    public Message shareTaskUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        TaskUser taskUser =  new TaskUser();
        String id =params.get("id");
        if ( StringUtils.isEmpty(id) ) {
            // 必须信息缺一不可,返回修改时关键信息缺失
            return new Message().error(5013, "信息缺失");
        }
        taskUser.setId(Long.parseLong(id));
        taskUser.setShare(true);
        if (taskUserService.editTaskUser(taskUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/share", "shareTaskUser", (short) 3006, "编辑成功"));
            return new Message().ok(5014, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/share", "shareTaskUser", (short) 3007, "编辑失败"));
            return new Message().error(5015, "编辑失败");
        }
    }

    @ApiOperation(value = "编辑TaskUser", notes = "修改TaskUser客户资料")
    @ResponseBody
    @PostMapping("/user/edit")
    public Message editTaskUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        TaskUser taskUser =  new TaskUser();
        String id =params.get("id");
        String mobile = params.get("mobile");
        String isShare = params.get("isShare");
        // 必须信息缺一不可,返回验证消息
        if (StringUtils.isEmpty(mobile) ||StringUtils.isEmpty(id)) {
            return new Message().error(5016, "信息不全");
        }
        taskUser.setId(Long.parseLong(id));
        taskUser.setMobile(mobile);

        if("true".equals(isShare)){
            taskUser.setShare(true);
        }else{
            taskUser.setShare(true);
        }
        if (taskUserService.editTaskUser(taskUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/edit", "editTaskUser", (short) 5014, "编辑成功"));
            return new Message().ok(5014, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/edit", "editTaskUser", (short) 5018, "编辑失败"));
            return new Message().error(5017, "编辑失败");
        }
    }

    @ApiOperation(value = "编辑Task", notes = "更新任务执行状态")
    @ResponseBody
    @PostMapping("/status")
    public Message editTaskStatus(HttpServletRequest request, HttpServletResponse response){
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        String status =params.get("status");
        if ( StringUtils.isEmpty(id) || StringUtils.isEmpty(status) ) {
            // 必须信息缺一不可,返回修改时关键信息缺失
            return new Message().error(5018, "信息缺失");
        }
        Task oldTask = new Task();
        oldTask = taskService.getTaskById(Long.parseLong(id));
        if(oldTask == null){
            return new Message().error(5031, "编辑失败");
        }
        //判断当前登陆用户是否有权限操作任务
        if(oldTask.getUserId().equals(appId)){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/status", "editTaskStatus", (short) 5028, "本人操作"));
        }else{
            //根据当前用户的appId查询该用户的最大权限
            String maxRoleId = accountService.loadAccountRoleId(appId);
            //根据roleid查询该角色是否为企业员工 code : role_company
            AuthRole authRole = roleService.selectByRoleId(maxRoleId);
            String uid = oldTask.getUserId();
            if(authRole.getCode().equals("role_company")){
                if(accountService.getUserByUidAndPid(uid,appId)!=null){
                    LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/status", "editTaskStatus", (short) 5029, "上级操作"));
                }else{
                    return new Message().ok(5030, "编辑失败");
                }
            }else{
                return new Message().error(5030, "编辑失败");
            }
        }

        Task newTask =  new Task();
        newTask.setId(Long.parseLong(id));
        newTask.setStatus(Byte.valueOf(status));
        if (taskService.editTask(newTask)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/status", "editTaskStatus", (short) 5019, "编辑成功"));
            return new Message().ok(5019, "编辑成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/status", "editTaskStatus", (short) 5020, "编辑失败"));
            return new Message().error(5020, "编辑失败");
        }
    }

    @ApiOperation(value = "获取Task", notes = "根据id获取Task信息")
    @ResponseBody
    @PostMapping("/select")
    public Object selectTaskById(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String appId =request.getHeader("appId");
        String id =params.get("id");
        if ( StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5021, "信息缺失");
        }
        Task task = taskService.getTaskById(Long.parseLong(id));
        if (task !=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/select", "selectTaskById", (short) 5022, "查询成功"));
            return new Message().ok(5022, "查询成功").addData("task",task);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/select", "selectTaskById", (short) 5023, "查询失败"));
            return new Message().error(5023, "查询失败");
        }
    }

    @ApiOperation(value = "获取Task列表信息", notes = "根据appId分页获取所有Task信息")
    @ResponseBody
    @PostMapping("/taskList")
    public Message selectTaskList(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
             int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
             int pageSize){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String appId =request.getHeader("appId");
        if ( StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5024, "信息缺失");
        }
        if (taskService.findAllTaskByAppId(pageNum,pageSize,appId)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/taskList", "selectTaskList", (short) 3010, "查询成功"));
            return new Message().ok(5025, "查询成功").addData("taskList",taskService.findAllTaskByAppId(pageNum,pageSize,appId));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/taskList", "selectTaskList", (short) 3011, "查询失败"));
            return new Message().error(5026, "查询失败");
        }
    }

    @ApiOperation(value = "获取TaskUser列表信息", notes = "根据条件分页获取所有TaskUser信息")
    @ResponseBody
    @PostMapping("/user/conditions")
    public Message selectTaskUserListByConditions(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                          int pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                                          int pageSize){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String appId =request.getHeader("appId");
        String test  =request.getHeader("test");
        String type  =request.getHeader("type");
        String share =request.getHeader("share");

        if ( StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5024, "信息缺失");
        }
        if (taskUserService.findAllTaskUser(pageNum,pageSize,appId,test,type,share)!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/conditions", "selectTaskUserListByConditions", (short) 3010, "查询成功"));
            return new Message().ok(5025, "查询成功").addData("taskList",taskUserService.findAllTaskUser(pageNum,pageSize,appId,test,type,share));
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/conditions", "selectTaskUserListByConditions", (short) 3011, "查询失败"));
            return new Message().error(5027, "查询失败");
        }
    }
}
