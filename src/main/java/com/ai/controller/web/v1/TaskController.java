package com.ai.controller.web.v1;

import com.ai.domain.bo.*;
import com.ai.domain.vo.Message;
import com.ai.service.*;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

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
            return new Message().ok(0, "success");
        } else {
            return new Message().error(5003, "新增失败");
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
            return new Message().error(5001, "信息缺失");
        }
        TaskUserReport taskUserReport = taskUserReportService.getTaskUserReportById(Long.parseLong(id));
        if (taskUserReport !=null){
            return new Message().ok(0, "success").addData("taskUserReport",taskUserReport);
        } else {
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
            return new Message().error(4004, "当前用户未登录！");
        }
        String taskName = params.get("taskName");
        if (StringUtils.isEmpty(taskName)) {
            return new Message().error(5007, "请填写任务名称");
        }
        String template_id = params.get("template");
        if (StringUtils.isEmpty(template_id) || template_id.equals("undefined")) {
            return new Message().error(5007, "请选择模板信息");
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
        PageInfo<Sim> simList= simService.findAllSim(1,10,user_id,"");
        List<SimUser> simUserList= simService.getListByUserId(user_id);
        if(simList.getTotal()==0  &&  simUserList.size()==0){
            return new Message().error(5005, "目前无法创建任务");
        }
        String test = params.get("test");
        Task task = new Task();
        task.setName(taskName);
        task.setUserId(user_id);
        if(!StringUtils.isEmpty(params.get("num"))){
            task.setThread(Integer.parseInt(params.get("num")));
        }
        task.setTotal(0);
        task.setThread(0);
        task.setCalled(0);
        task.setTemplateId(Long.parseLong(template_id));
        task.setCreatedAt(new Date());
        task.setStatus(new Byte("1"));
        if (test.equals("1")) {
            task.setTest(true);
            task.setStatus((byte)2); // 如果是测试任务，直接设置为开始状态
            String testPhone = params.get("testPhone");
            if (StringUtils.isEmpty(testPhone)) {
                return new Message().error(5034, "请填写手机号码");
            }
        } else {
            task.setTest(false);
        }

        if (taskService.registerTask(task)) {
            if(task.getTest()){
                String testName = params.get("testName");
                String testPhone = params.get("testPhone");
                String remark = params.get("remark");
                if (StringUtils.isEmpty(testPhone)) {
                    return new Message().error(5034, "请填写手机号码");
                }
                Long taskid = task.getId();
                TaskUser taskUser = new TaskUser();
                taskUser.setTaskId(taskid);
                taskUser.setName(testName);
                taskUser.setMobile(testPhone);
                taskUser.setRemark(remark);
                taskUser.setCalledAt(new Date());
                if(!taskUserService.addTaskUser(taskUser)){
                    return new Message().error(5012, "新增失败");
                }

                // 获取当前任务发送用户能使用的卡
                List<Sim> sims = new ArrayList<>();
                sims.addAll(simList.getList());
                for (SimUser su: simUserList){
                    Sim s = new Sim();
                    s.setId(su.getSimId());
                    sims.add(s);
                }
                // 通知每一张卡
                for (Sim s:sims) {
                    String v = redisTemplate.opsForValue().get("task_start_"+s.getId());
                    List<String> arr = new ArrayList<>();
                    if (v!=null) {
                        arr = new ArrayList<String>(Arrays.asList(v.split(",")));
                    }
                    arr.add(task.getId()+"");
                    redisTemplate.opsForValue().set("task_start_"+s.getId(), String.join(",", org.apache.commons.lang.StringUtils.join(arr.toArray(),",")));
                }
            }
            return new Message().ok(0, "success");
        } else {
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
            return new Message().ok(0, "success");
        } else {
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
            return new Message().error(5021, "信息缺失");
        }
        taskUser.setId(Long.parseLong(id));
        taskUser.setShare(true);
        if (taskUserService.editTaskUser(taskUser)) {
            return new Message().ok(0, "success");
        } else {
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
            return new Message().ok(0, "success");
        } else {
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
            return new Message().error(5022, "信息缺失");
        }
        Task oldTask = new Task();
        oldTask = taskService.getTaskById(Long.parseLong(id));
        if(oldTask == null){
            return new Message().error(5031, "当前任务不存在");
        }
        if(status.equals("2") && oldTask.getStatus()==2){
            return new Message().error(5032, "当前任务正在执行中");
        }
        if(status.equals("4") && oldTask.getStatus()==4){
            return new Message().error(5037, "当前任务已暂停");
        }
        if(status.equals("2")){
            if(oldTask.getStatus()!=1  ){
                if(oldTask.getStatus()!=4){
                    return new Message().error(5032, "当前任务不在执行列表中");
                }
            }
        }
        if(status.equals("4")){
            if(oldTask.getStatus()!=2  ){
                if(oldTask.getStatus()!=3){
                    return new Message().error(5032, "当前任务不在执行列表中");
                }
            }
        }
        PageInfo<TaskUser> taskUserList = taskUserService.findAllTaskUser(1,500,appId,id,"","","","");
        if(taskUserList.getSize()==0){
            return new Message().error(5035, "当前任务没有客户信息，请在导入后继续操作");
        }

        //判断当前登陆用户是否有权限操作任务
        if(oldTask.getUserId().equals(appId)){
        }else{
            //根据当前用户的appId查询该用户的最大权限
            String maxRoleId = accountService.loadAccountRoleId(appId);
            //根据roleid查询该角色是否为企业员工 code : role_company
            AuthRole authRole = roleService.selectByRoleId(maxRoleId);
            String uid = oldTask.getUserId();
            if(authRole.getCode().equals("role_company")){
                if(accountService.getUserByUidAndPid(uid,appId)!=null){
                }else{
                    return new Message().error(5030, "编辑失败");
                }
            }else{
                return new Message().error(5030, "编辑失败");
            }
        }
        Task newTask =  new Task();
        newTask.setId(Long.parseLong(id));
        newTask.setStartAt(new Date());
        newTask.setUpdateAt((new Date()));
        newTask.setStatus(Byte.valueOf(status));
        if (taskService.editTask(newTask)) {
            return new Message().ok(0, "success");
        } else {
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
            return new Message().error(5013, "信息缺失");
        }
        Task task = taskService.getTaskById(Long.parseLong(id));
        if (task !=null){
            return new Message().ok(0, "success").addData("task",task);
        } else {
            return new Message().error(5023, "查询失败");
        }
    }

    @ApiOperation(value = "获取Task列表信息", notes = "根据appId分页获取所有Task信息")
    @ResponseBody
    @PostMapping("/taskList")
    public Message selectTaskList(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
             int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20")
             int pageSize){
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String appId =request.getHeader("appId");
        pageNum = Integer.parseInt(params.get("page"));
        String name = params.get("name");
        if ( StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
//        String roleId= accountService.loadAccountRoleId(appId);
//        if(roleId.equals("100")){
//            appId = "";
//        }
        PageInfo<Task> taskList = taskService.findAllTaskByAppId(pageNum,pageSize,appId,name);
        if (taskList!=null){
            return new Message().ok(0, "success").addData("taskList",taskList);
        } else {
            return new Message().error(5026, "查询失败");
        }
    }

    @ApiOperation(value = "获取TaskUser列表信息", notes = "根据条件分页获取所有TaskUser信息")
    @ResponseBody
    @PostMapping("/user/conditions")
    public Message selectTaskUserListByConditions(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                          int pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "16")
                                          int pageSize){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        //String test  =params.get("test");
        String status  =params.get("status");
        String share =params.get("share");
        String type  =params.get("type");
        String name  =params.get("name");
        String taskId =params.get("taskId");
        pageNum = Integer.parseInt(params.get("page"));
//        String roleId= accountService.loadAccountRoleId(appId);
//            if(roleId.equals("100")){
//            appId = "";
//        }
        PageInfo<TaskUser> taskUserList = taskUserService.findAllTaskUser(pageNum,pageSize,appId,taskId,name,type,share,status);
        if (taskUserList != null){
            return new Message().ok(0, "success").addData("taskUserList",taskUserList);
        } else {
            return new Message().error(5027, "查询失败");
        }
    }

    @ApiOperation(value = "导入excel", notes = "根据规定的模板导入客户信息")
    @ResponseBody
    @PostMapping("/imp")
    public Message ImportExcel(@RequestParam("file") MultipartFile file ,@RequestParam("id") String id) throws Exception {
        if(StringUtils.isEmpty(id)){
            return new Message().error(5021,"信息缺失");
        }
        if (file == null) {
            return new Message().error(5025,"导入失败");
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
            return new Message().error(4004, "缺少授权信息");
        }
        if (StringUtils.isEmpty(taskId)) {
            return new Message().error(5021, "缺少信息");
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
