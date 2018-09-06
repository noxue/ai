package com.ai.controller.web.v1;

import com.ai.domain.bo.*;
import com.ai.domain.vo.Message;
import com.ai.service.*;
import com.ai.test.test;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,patch部分更新,del删除
 * @2018年6月9日17:50:34
 */
@RestController
@RequestMapping("/web/api/v1/task")
public class TaskController extends BasicAction{

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskUserService taskUserService;

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

    /* *
     * @Description 根据taskUserId获取TaskUserReport信息
     * @Param [] taskUserId
     * @Return com.ai.domain.bo.TaskUserReport.java
     */
    @ApiOperation(value = "获取TaskUserReport", notes = "根据id获取TaskUserReport信息")
    @ResponseBody
    @PostMapping("/user/{id}/report")
    public Message selectTaskUserReportById(HttpServletRequest request,@PathVariable int id){

        if ( id<=0) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5001, "信息缺失");
        }
        TaskUser taskUser = taskUserService.getTaskUserById(id);
        if (taskUser !=null){
            return new Message().ok(0, "success").addData("report",taskUser);
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
        String taskName = params.get("taskName");
        if (StringUtils.isEmpty(taskName)) {
            return new Message().error(5007, "请填写任务名称");
        }
        String template_id = params.get("template");
        if (StringUtils.isEmpty(template_id) || template_id.equals("undefined")) {
            return new Message().error(5007, "请选择模板信息");
        }


        PageInfo<Sim> simList= simService.findAllSim(1,1000,user_id,"");
        List<SimUser> simUserList= simService.getListByUserId(user_id);
        if(simList.getTotal()==0  &&  simUserList.size()==0){
            return new Message().error(5005, "请先分配sim卡后再创建任务");
        }
        String test = params.get("test");
        Task task = new Task();
        task.setName(taskName);
        task.setUserId(user_id);
        if(!StringUtils.isEmpty(params.get("num"))){
            task.setThread(Integer.parseInt(params.get("num")));
        }

        task.setFollow(params.get("follows"));

        if(!StringUtils.isEmpty(params.get("break"))){
            task.setInterrupt(Integer.parseInt(params.get("break")));
        }
        task.setTotal(0);
        task.setThread(1);
        task.setCalled(0);
        task.setTemplateId(Long.parseLong(template_id));
        task.setCreatedAt(new Date());
        task.setStatus((byte)1);
        if (test.equals("1")) {
            String testPhone = params.get("testPhone");
            if (StringUtils.isEmpty(testPhone)) {
                return new Message().error(5034, "请填写电话号码");
            }
            String regex="^[0-9-]{8,}$";
            if(!Pattern.matches(regex, testPhone)){
                return new Message().error(4001, "请输入正确的电话号码");
            }
            task.setTest(true);
            task.setStatus((byte)2); // 如果是测试任务，直接设置为开始状态
            task.setStartAt(new Date());
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
                task.setStatus((byte)2);
                taskService.editTask(task);
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
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String id =params.get("id");
        String mobile = params.get("mobile");
        String name = params.get("name");
        String type = params.get("type");
        String remark = params.get("remark");
        // 必须信息缺一不可,返回验证消息
        if (StringUtils.isEmpty(appId)) {
            return new Message().error(4004, "当前用户未登录");
        }
        if (StringUtils.isEmpty(mobile) ||StringUtils.isEmpty(id)) {
            return new Message().error(5016, "信息不全");
        }
        TaskUser taskUser =  new TaskUser();
        taskUser.setId(Long.parseLong(id));
        taskUser.setMobile(mobile);
        taskUser.setName(name);
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
        if(oldTask.getStatus() == 0){
            return new Message().error(5043, "当前任务已结束");
        }
        PageInfo<TaskUser> taskUserList = taskUserService.findAllTaskUser(1,500,appId,id,"","","","");
        if(taskUserList.getSize()==0){
            return new Message().error(5035, "当前任务没有客户信息，请在导入后继续操作");
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

        if(status.equals("2")){
            newTask.setStartAt(new Date());
        }

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

    @ApiOperation(value = "获取TaskUser", notes = "根据id获取TaskUser信息")
    @ResponseBody
    @GetMapping("/user/{id}")
    public Object selectTaskUserById(@PathVariable int id){
        if (!(id>0)) {
            // 必须信息缺一不可,返回信息不全
            return new Message().error(4407, "id值不合法");
        }
        TaskUser taskUser = taskUserService.getTaskUserById(id);
        if (taskUser !=null){
            return new Message().ok(0, "success").addData("taskUser",taskUser);
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

    @ApiOperation(value = "导出excel，版本2", notes = "步骤1：将获取到的条件信息存入redis")
    @ResponseBody
    @PostMapping("/expExc")
    public Message ExportExcelSecond(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String taskId = params.get("taskId");
        if (StringUtils.isEmpty(appId)) {
            return new Message().error(4004, "缺少授权信息");
        }
        if (StringUtils.isEmpty(taskId)) {
            return new Message().error(5021, "缺少信息");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String form = appId +","+taskId+","+
                params.get("name") +","+
                params.get("type") +"," +
                params.get("share")+","+
                params.get("status");
        redisTemplate.opsForValue().set(uuid,form);
        // redisTemplate.expire(uuid,60L,null);
        return new Message().ok(0,"success").addData("task",uuid);
    }

    @ApiOperation(value = "导出excel，版本2", notes = "步骤2：根据uuid查询缓存中的条件信息")
    @ResponseBody
    @GetMapping("/{id}")
    public void expExc2(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id ) throws Exception {
        response.setContentType("application/force-download");
        response.setCharacterEncoding("gbk");
        response.setContentType("application/octet-stream");//告诉浏览器输出内容为流
        response.setHeader("Content-Disposition","attachment; filename=detail.csv");
        PrintWriter out = response.getWriter();//放在第一句是会出现乱码

        String condition= redisTemplate.opsForValue().get(id);
        String form[] = new String[6];
        if(!StringUtils.isEmpty( condition)){
            String[] strs=condition.split(",");
            for(int i=0,len=strs.length;i<len;i++){
                form[i] = strs[i].toString();
            }
        }

        String data = "客户姓名,客户电话,呼叫状态,客户分类,分类原因,呼叫时间,通话时间,跟进状态\n";
        long total = 0;
        int sum = 0;
        do{
            PageInfo<TaskUser> taskUserList = taskUserService.exportTaskUser(sum++,500,
                    form[0],form[1],form[2],form[3],form[4],form[5]);
            total = taskUserList.getTotal();
            List<String[]>  listArray = excelService.formatList(taskUserList.getList());
            for (int i = 0; i < listArray.size(); i++) {
                for (int j = 0; j < listArray.get(i).length; j++) {
                    data += listArray.get(i)[j] + ",";
                }
                out.print(data);
                data = "\r\n";
            }
        }
        while (sum*500 < total);
        out.flush();
        out.close();
    }

    @ApiOperation(value = "删除任务", notes = "删除当前选中的任务")
    @ResponseBody
    @PostMapping("/del")
    public Message delTask(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String taskId = params.get("id");
        if (StringUtils.isEmpty(taskId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(5042, "当前无法操作");
        }
        boolean flag = taskService.delTask(Long.parseLong(taskId));
        if (flag){
            return new Message().ok(0, "success");
        } else {
            return new Message().error(5041, "删除失败");
        }
    }

    @ApiOperation(value = "今日呼叫模块", notes = "获取后台处理完的数据集合")
    @ResponseBody
    @PostMapping("/todayCount")
    public Message getTodayCount(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Object [] data = taskUserService.getCountToday(appId);
        return new Message().ok(0, "success").addData("today",data);
    }

    @ApiOperation(value = "首页待处理区", notes = "待处理信息")
    @ResponseBody
    @GetMapping("/todo")
    public Message countTask(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失et
            return new Message().error(4004, "当前用户未登录");
        }
        int num = taskService.getTaskCount(appId);
        int userNum = taskUserService.getTaskUserCount(appId);
        int [] count = new int[]{num,userNum};
        if (num > -1 ){
            return new Message().ok(0, "success").addData("countTask",count);
        } else {
            return new Message().error(5027, "查询失败");
        }
    }

    @ApiOperation(value = "首页统计数据", notes = "根据时间统计客户类型的数量，默认时间为过去一周")
    @ResponseBody
    @PostMapping("/user/count")
    public Message countTaskUserList(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String staTime = params.get("staTime");
        String endTime = params.get("endTime");
        List<String> num = taskUserService.getTaskUserCount(appId,staTime,endTime);
        if (num != null){
            return new Message().ok(0, "success").addData("countType",num);
        } else {
            return new Message().error(5027, "查询失败");
        }
    }

    @ApiOperation(value = "统计今日数据", notes = "待处理任务")
    @ResponseBody
    @PostMapping("/toDayTask")
    public Message toDayTask(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        int num = taskService.getTaskCount(appId);
        if (num > 0 ){
            return new Message().ok(0, "success").addData("countTask",num);
        } else {
            return new Message().error(5027, "查询失败");
        }
    }

    @ApiOperation(value = "重拨", notes = "只对测试任务生效")
    @ResponseBody
    @PostMapping("/toRedial")
    public Message toRedial(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String taskId =  params.get("taskId");
        String taskUserId =  params.get("taskUserId");
        if(StringUtils.isEmpty(taskId) || StringUtils.isEmpty(taskUserId)){
            return new Message().error(5021, "信息缺失");
        }
        Task t = taskService.getTaskById(Long.parseLong(taskId));
        if(t ==null){
            return new Message().error(5031, "当前任务不存在");
        }
        if(!t.getTest()){
            return new Message().error(5038, "当前任务为正式任务");
        }
        t.setStatus((byte)2);
        if(taskService.editTask(t)){
            TaskUser user = taskUserService.getTaskUserById(Long.parseLong(taskUserId));
            user.setStatus((byte)1);
            if(taskUserService.editTaskUser(user)){
                return new Message().ok(0, "success");
            }else{
                return new Message().error(5040, "重拨失败");
            }
        }else{
            return new Message().error(5039, "重拨失败");
        }
    }

    @ApiOperation(value = "taskUser任务详情,图表数据", notes = "根据当前用户，taskId查询当前type各类的数量")
    @ResponseBody
    @PostMapping("/user/type")
    public Message countUserTypeByTaskId(HttpServletRequest request, HttpServletResponse response){
        String appId =request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String taskId = params.get("taskId");
        List<String> num = taskUserService.getUserType(taskId);
        if (num != null){
            return new Message().ok(0, "success").addData("userType",num);
        } else {
            return new Message().error(5027, "查询失败");
        }
    }

}
