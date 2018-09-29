package com.ai.controller.robot.v1;

import com.ai.controller.robot.v1.bean.Auth;
import com.ai.controller.robot.v1.bean.BaseAction;
import com.ai.controller.robot.v1.utils.Utils;
import com.ai.domain.bo.*;
import com.ai.domain.vo.Message;
import com.ai.service.*;
import com.ai.util.RequestResponseUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;


@Component
public class WebSocketServeice {

    final public static List<WebSocketMsg> msgs = new LinkedList<>();

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserConfigService userConfigService;

    @Autowired
    private SimService simService;

    @Autowired
    private TemplateService templateService;

    private Map<WebSocketSession, String> users = new HashMap<>();

    public void deleteUser(WebSocketSession session) {
        synchronized (users){
            users.remove(session);
            System.out.println("有用户退出");
        }
    }

    public String on_auth(BeanUtil beanUtil, WebSocketSession session, String content) {

        Auth auth = Utils.Json2Bean(content, Auth.class);
        String appid = auth.getId();
        String key = auth.getKey();

        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "AuthFinish");
        map.put("code", 0);
        if (appid == null || "".equals(appid.trim()) || key == null || key.length() != 16) {
            map.put("code", -1);
            map.put("data", "appid or key is empty");
            return gson.toJson(map);
        }

        String robot_key = beanUtil.getRedisTemplate().opsForValue().get("robot_" + appid);
        if (robot_key == null || "".equals(robot_key)) {
            App app = beanUtil.getAppService().getAppById(Long.parseLong(appid));
            if (app != null) {
                robot_key = app.getKey();
                beanUtil.getRedisTemplate().opsForValue().set("robot_" + appid, key);
            }
        }

        if (!key.equals(robot_key)) {
            map.put("code", -2);
            map.put("data", "appid or key is not match");
        }
        WebSocketSession oldSession  = beanUtil.getWebSocketServeice().getSession(appid);
        if (null!=oldSession){
            beanUtil.getWebSocketServeice().getUsers().remove(oldSession);
        }
        beanUtil.getWebSocketServeice().getUsers().put(session, appid);
        return gson.toJson(map);
    }

    public boolean workTime(String appid, String uid) {
        UserConfig config = userConfigService.getConfigByUserId(uid, "schedule");
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "WorkTimeUpdate");
        map.put("code", 0);

        Map map1 = new HashMap();
        map1.put("uid", config.getUserId());
        map1.put("worktime", config.getValue());
        map.put("data", gson.toJson(map1));

        return sendMap(appid, map);
    }

    private Map getTplMap(TemplateService templateService, long id) {
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "TplUpdate");
        map.put("code", 0);
        try {
            Template template = templateService.getTemplateById(id);
            if (template == null) {
                map.put("code", -1);
                map.put("data", "not found this template id:" + id);
            } else {
                map.put("data", gson.toJson(template));
            }
        } catch (NumberFormatException ex) {
            map.put("code", -2);
            map.put("data", "template id format is not a number:" + id);
        }
        return map;
    }

    public String on_tpl(BeanUtil beanUtil, WebSocketSession session, String content) {
        String idStr = new JsonParser().parse(content).getAsJsonObject().get("content").getAsString();
        Gson gson = new Gson();
        return gson.toJson(getTplMap(beanUtil.getTemplateService(), Long.parseLong(idStr)));
    }

    public boolean tpl(String appid, long id) {
        return sendMap(appid, getTplMap(templateService, id));
    }

    public boolean sim(String appid, long id) {
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "SimUpdate");
        map.put("code", 0);
        Sim sim = this.simService.getSimById(id);
        if (sim == null) {
            map.put("code", -1);
            map.put("data", "not found this sim id:" + id);
        } else {
            map.put("data", gson.toJson(sim));
        }
        return sendMap(appid, map);
    }

    public String on_sim_tasks(BeanUtil beanUtil, WebSocketSession session, String content) {
        String idStr = new JsonParser().parse(content).getAsJsonObject().get("content").getAsString();
        long sim_id = Long.parseLong(idStr);

        Map map = new HashMap();
        map.put("action", "TasksUpdate");
        map.put("code", 0);

        if (sim_id <= 0) {
            map.put("code", -1);
            map.put("data", "param sim_id format is invalid");
        }
        PageInfo<SimUser> SimUserList = beanUtil.getSimService().findSimUserBySimId(1, 1000, sim_id + "");
        if (SimUserList == null) {
            map.put("code", -2);
            map.put("data", "select simUser failed");
        }

        List<String> ids = new ArrayList<>();
        List<SimUser> users = SimUserList.getList();
        for (SimUser user : users) {
            ids.add(user.getUserId());
        }

        Sim sim = beanUtil.getSimService().getSimById(sim_id);
        if (sim != null) {
            ids.add(sim.getUserId());
        }

        //根据userId获取task集合
        List<Task> tasks = beanUtil.getTaskService().getStartedTasksByUserId(ids);
        if (tasks.size() > 0) {
            for (Task t : tasks) {
                t.setStatus((byte) 3);
                beanUtil.getTaskService().editTask(t);
            }
            Map map1 = new HashMap();
            map1.put("sim_id", sim_id);
            map1.put("tasks", new Gson().toJson(tasks));
            map.put("data", new Gson().toJson(map1));
        } else {
            map.put("code", -3);
            map.put("data", "not found tasks");
        }
        return new Gson().toJson(map);
    }


    public String on_sip_tasks(BeanUtil beanUtil, WebSocketSession session, String content) {
        String idStr = new JsonParser().parse(content).getAsJsonObject().get("content").getAsString();
        long sip_id = Long.parseLong(idStr);
        Map map = new HashMap();
        map.put("action", "TasksUpdate");
        map.put("code", 0);

        if (sip_id <= 0) {
            map.put("code", -1);
            map.put("data", "param sip_id format is invalid");
        }


        List<Task> tasks = beanUtil.getTaskSipService().getTasksBySip(sip_id);
        if (tasks!=null && tasks.size() > 0) {
            for (Task t : tasks) {
                t.setStatus((byte) 3);
                beanUtil.getTaskService().editTask(t);
            }
            Map map1 = new HashMap();
            map1.put("sip_id", sip_id);
            map1.put("tasks", new Gson().toJson(tasks));
            map.put("data", new Gson().toJson(map1));
        } else {
            map.put("code", -2);
            map.put("data", "sip task not found,sip id:"+sip_id);
        }
        return new Gson().toJson(map);
    }

    public String task_user(BeanUtil beanUtil, WebSocketSession session, String content, String userType) {
        Map map = new HashMap();
        map.put("action", "TaskUserUpdate");
        map.put("code", 0);


        long taskId = new JsonParser().parse(content).getAsJsonObject().get("task_id").getAsLong();
        long id = new JsonParser().parse(content).getAsJsonObject().get("id").getAsLong();

        Task task = beanUtil.getTaskService().getTaskById(taskId);
        if (task == null) {
            map.put("code", -1);
            map.put("data", "not found the task, id:" + taskId);
            return new Gson().toJson(map);
        }
        int status = task.getStatus();

        if (status != 3 ) {
            map.put("code", -2);
            map.put("data", "this task is not running, id:" + taskId);
            beanUtil.getWebSocketServeice().deleteTask(beanUtil.getWebSocketServeice().getUsers().get(session), (int) taskId);
            return "";
        }
        List<TaskUser> taskUserList = beanUtil.getTaskUserService().getTaskUserAndUpdate(taskId);

        if (taskUserList.size() > 0) {
            task.setCalled(task.getCalled() + taskUserList.size());
        } else {
            task.setFinishAt(new Date());
            task.setStatus((byte) 0);
            map.put("code", -3);
            map.put("data", "task is finished");
        }

        if (!beanUtil.getTaskService().editTask(task)) {
            map.put("code",-4);
            map.put("data","edit task failed");
        }

        if (!map.get("code").toString().equals("0")) {
            beanUtil.getWebSocketServeice().deleteTask(beanUtil.getWebSocketServeice().getUsers().get(session), (int) taskId);
            return "";
        }

        TaskUser taskUser = new TaskUser();
        if (taskUserList.size()>0){
            taskUser = taskUserList.get(0);
        }

        Map map1 = new HashMap();
        map1.put("user",new Gson().toJson(taskUser));
        map1.put("type",userType);
        if ("sim".equals(userType)){
            map1.put("sim_id",id);
        } else {
            map1.put("sip_id",id);
        }
        map.put("data", new Gson().toJson(map1));

        return new Gson().toJson(map);
    }
    public String on_sip_task_user(BeanUtil beanUtil, WebSocketSession session, String content) {
        return this.task_user(beanUtil, session, content,"sip");
    }
    public String on_sim_task_user(BeanUtil beanUtil, WebSocketSession session, String content) {
        return this.task_user(beanUtil, session, content,"sim");
    }

    /**
     * @param appid
     * @param id    task id
     * @return
     */
    public boolean task(String appid, long id) {
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "TaskUpdate");
        map.put("code", 0);
        Task task = this.taskService.getTaskById(id);
        if (task == null) {
            map.put("code", -1);
            map.put("data", "not found this task id:" + id);
        } else {
            map.put("data", gson.toJson(task));
        }
        return sendMap(appid, map);
    }

    boolean sendMap(String appid, Map map) {
        boolean ok = false;
        Gson gson = new Gson();
        WebSocketSession session = getSession(appid);

        if (session == null) {
            map.put("code", -1);
            map.put("data", "websocket session is not found");
        } else {
            ok = true;
        }

        WebSocketServeice.putMsg(session, gson.toJson(map) + "\r\n\r\n");

        return ok;
    }

    public WebSocketSession getSession(String appid) {
        for (WebSocketSession session : this.getUsers().keySet()) {
            if (this.users.get(session).equals(appid)) {
                return session;
            }
        }
        return null;
    }

    public boolean delete(String name, String appid, int id) {
        if (appid == null || "".equals(appid)) {
            System.out.println("appid is empty");
            return false;
        }
        WebSocketSession session = getSession(appid);
        if (session == null) {
            System.out.println("get session failed");
            return false;
        }
        Map map = new HashMap();
        map.put("action", name);
        map.put("data", id + "");

        return sendMap(appid, map);
    }

    /**
     * notify robot to delete template
     *
     * @param appid robot appid
     * @param tplId template id
     * @return if success return true
     */
    public boolean deleteTpl(String appid, int tplId) {
        return this.delete("TplDelete", appid, tplId);
    }


    public boolean deleteGateway(String appid, int tplId) {
        return this.delete("GatewayDelete", appid, tplId);
    }

    public boolean deleteTask(String appid, int taskId) {
        return this.delete("TaskDelete", appid, taskId);
    }

    public boolean deleteSim(String appid, int simId) {
        return this.delete("SimDelete", appid, simId);
    }

    public boolean gateways(String appid) {
        PageInfo<Gateway> pageInfo = gatewayService.findGatewaysByAppId(1, 1000000, appid);

        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "GatewaysUpdate");
        map.put("code", 0);
        map.put("data", gson.toJson(pageInfo.getList()));
        return sendMap(appid, map);
    }

    /**
     * @param appid
     * @param id    gateway id
     * @return
     */
    public boolean gateway(String appid, long id) {
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("action", "GatewayUpdate");
        map.put("code", 0);
        Gateway gateway = gatewayService.getGateById(id);
        if (gateway == null) {
            map.put("code", -1);
            map.put("data", "gateway is not found, id:" + id);
        } else {
            map.put("data", gson.toJson(gateway));
        }
        return sendMap(appid, map);
    }


    public Map<WebSocketSession, String> getUsers() {
        return users;
    }

    public void setUsers(Map<WebSocketSession, String> users) {
        this.users = users;
    }



    public static boolean putMsg(WebSocketSession session, String msg) {
        boolean ok = false;
        synchronized (WebSocketServeice.msgs) {
            ok = WebSocketServeice.msgs.add(new WebSocketMsg(session, msg));
        }
        return ok;
    }

    public static WebSocketMsg getMsg() {
        synchronized (WebSocketServeice.msgs) {
            for (WebSocketMsg msg : WebSocketServeice.msgs) {

                return msg;
            }
        }
        return null;
    }

    public static WebSocketMsg deleteMsg(WebSocketMsg msg) {
        synchronized (WebSocketServeice.msgs) {
            WebSocketServeice.msgs.remove(msg);
        }
        return null;
    }


}

