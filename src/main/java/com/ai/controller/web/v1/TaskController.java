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
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private ExcelService excelService;

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
    public Message addTask(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        //获取参数
        String user_id =  request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        //String sim_id = params.get("sim");
        // 必须信息缺一不可,返回验证消息
        if (StringUtils.isEmpty(user_id)) {
            return new Message().error(5007, "当前用户未登录！");
        }
        /*if (StringUtils.isEmpty(sim_id)) {
            return new Message().error(5007, "请选择sim卡信息");
        }
        //验证当前用户与选中的卡是否匹配
        if(simService.isExistInSimUser(user_id,sim_id)){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "", "isExistInSim", (short) 5019, "验证成功"));
        }else {
            return new Message().error(5018, "该卡当前不可使用");
        }*/
        String taskName = params.get("taskName");
        if (StringUtils.isEmpty(taskName)) {
            return new Message().error(5007, "请填写任务名称");
        }
        String template_id = params.get("template");
        if (StringUtils.isEmpty(template_id)) {
            return new Message().error(5007, "请选择模板信息");
        }

        String test = params.get("test");
        //String date1 = params.get("date1");
        //String date2 = params.get("date2");
        Task task = new Task();
        task.setName(taskName);
        task.setUserId(user_id);
        if(!StringUtils.isEmpty(params.get("num"))){
            task.setThread(Integer.parseInt(params.get("num")));
        }
//        if(!StringUtils.isEmpty(params.get("total"))){
//            task.setTotal(Integer.parseInt(params.get("total")));
//        }
        task.setTotal(0);
        task.setTemplateId(Long.parseLong(template_id));
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //task.setStartAt(sdf.parse(date1+" "+ date2));
        task.setCreatedAt(new Date());
        task.setStatus(new Byte("1"));
        if (test.equals("1")) {
            task.setTest(false);
        } else {
            task.setTest(true);
        }
        if (taskService.registerTask(task)) {
            if(test.equals("0")){
                String testName = params.get("testName");
                String testPhone = params.get("testPhone");
                String remark = params.get("remark");
                if (StringUtils.isEmpty(testPhone)) {
                    return new Message().error(5007, "请填写手机号码");
                }
                Long taskid = task.getId();
                TaskUser taskUser = new TaskUser();
                taskUser.setTaskId(taskid);
                taskUser.setName(testName);
                taskUser.setMobile(testPhone);
                taskUser.setRemark(remark);
                taskUser.setCalledAt(new Date());
                if(taskUserService.addTaskUser(taskUser)){
                    LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/add", "addTaskUser", (short) 5011, "新增成功"));
                }else{
                    LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/add", "addTaskUser", (short) 5012, "新增失败"));
                    return new Message().error(5012, "新增失败");
                }

            }else{
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/add", "addTask", (short) 3003, "新增成功"));
            }
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
        String type = params.get("type");
        String remark = params.get("remark");
        //String isShare = params.get("share");

        // 必须信息缺一不可,返回验证消息
        if (StringUtils.isEmpty(mobile) ||StringUtils.isEmpty(id)) {
            return new Message().error(5016, "信息不全");
        }
        taskUser.setId(Long.parseLong(id));
        taskUser.setMobile(mobile);
        taskUser.setRemark(remark);
        if(!StringUtils.isEmpty(type)){
            taskUser.setType(Byte.valueOf(type));
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
        if((oldTask.getStatus().toString()).equals("2")){
            return new Message().error(5031, "当前任务正在执行中");
        }
        PageInfo<TaskUser> taskUserList = taskUserService.findAllTaskUser(1,500,appId,id,"","","","");
        if(taskUserList.getSize()==0){
            return new Message().error(5031, "当前任务没有客户信息，请在导入后继续操作");
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
        newTask.setStartAt(new Date());
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
        pageNum = Integer.parseInt(params.get("page"));
        String name = params.get("name");
        if ( StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5024, "信息缺失");
        }
        String roleId= accountService.loadAccountRoleId(appId);
        if(roleId.equals("100")){
            appId = "";
        }
        PageInfo<Task> taskList = taskService.findAllTaskByAppId(pageNum,pageSize,appId,name);
        if (taskList!=null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/taskList", "selectTaskList", (short) 3010, "查询成功"));
            return new Message().ok(5025, "查询成功").addData("taskList",taskList);
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
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5024, "信息缺失");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        //String test  =params.get("test");
        String status  =params.get("status");
        String share =params.get("share");
        String type  =params.get("type");
        String name  =params.get("name");
        String taskId =params.get("taskId");
        pageNum = Integer.parseInt(params.get("page"));
        String roleId= accountService.loadAccountRoleId(appId);
            if(roleId.equals("100")){
            appId = "";
        }

        PageInfo<TaskUser> taskUserList = taskUserService.findAllTaskUser(pageNum,pageSize,appId,taskId,name,type,share,status);
        if (taskUserList != null){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/conditions", "selectTaskUserListByConditions", (short) 3010, "查询成功"));
            return new Message().ok(5025, "查询成功").addData("taskUserList",taskUserList);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog( "admin", "/user/conditions", "selectTaskUserListByConditions", (short) 3011, "查询失败"));
            return new Message().error(5027, "查询失败");
        }
    }

    @ApiOperation(value = "导入excel", notes = "根据规定的模板导入客户信息")
    @ResponseBody
    @PostMapping("/imp")
    public Message ImportExcel(@RequestParam("file") MultipartFile file ,@RequestParam("id") String id) throws Exception {
        if(StringUtils.isEmpty(id)){
            return new Message().error(-1,"信息缺失");
        }
        if (file == null) {
            return new Message().error(-1,"导入失败");
        }
        return excelService.importExcel(Integer.parseInt(id),file);
    }

    @ApiOperation(value = "导出excel", notes = "根据当前的用户信息按照规定的模板导出execl信息")
    @ResponseBody
    @RequestMapping("/exp")
    public Message ExportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String taskId = params.get("taskId");
        if (StringUtils.isEmpty(appId)) {
            return new Message().error(-1, "缺少授权信息");
        }
        if (StringUtils.isEmpty(taskId)) {
            return new Message().error(-1, "缺少信息");
        }
        TaskUser[] result = taskUserService.taskUserList(appId,taskId);
        List<String[]> listArray = excelService.downloadExcel(result);
        // 指定允许其他域名访问    // 响应类型
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        String data = "";
        data += "客户姓名\t客户电话\t呼叫状态\t客户分类\t分类原因\t呼叫时间\t通话时间\t跟进状态\r\n";
        //填充数据
        for (int i = 0; i < listArray.size(); i++) {
            for (int j = 0; j < listArray.get(i).length; j++) {
                data += listArray.get(i)[j] + "\t";
            }
            data += "\r\n";
        }
        return new Message().ok(0,"success").addData("task",new String(Base64.encodeBase64(data.getBytes("gbk"))));
    }

}
