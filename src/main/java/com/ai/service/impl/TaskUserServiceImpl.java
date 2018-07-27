package com.ai.service.impl;

import com.ai.dao.TaskUserDao;
import com.ai.domain.bo.TaskUser;
import com.ai.domain.bo.TaskUserExample;
import com.ai.service.TaskUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("TaskUserService")
public class TaskUserServiceImpl implements TaskUserService {

    @Autowired
    private TaskUserDao taskUserDao;

    @Override
    public PageInfo<TaskUser> findAllTaskUser(int pageNum, int pageSize,String userId, String taskId, String name,
                                              String type, String share, String status) {
        PageHelper.startPage(pageNum, pageSize);
        List<TaskUser> taskUserList = taskUserDao.selectTaskUserListByConditions(userId,taskId,name,type,share,status);
        PageInfo result = new PageInfo(taskUserList);
        return result;
    }

    @Override
    public List<TaskUser> selectTaskUserByTaskId(int id) {
        List<TaskUser> taskUserList = taskUserDao.getTaskUserByTaskId(id);
        return taskUserList;
    }

    @Override
    public List<TaskUser> getTaskUserAndUpdate(long taskId) {
        PageHelper.startPage(1, 5);
        TaskUserExample taskUserExample = new TaskUserExample();
        taskUserExample.setOrderByClause("id asc");
        TaskUserExample.Criteria cr = taskUserExample.createCriteria();
        cr.andStatusEqualTo((byte)1);
        cr.andTaskIdEqualTo(taskId);

        List<TaskUser> users = taskUserDao.selectByExample(taskUserExample);
        for(TaskUser taskUser : users){
            taskUser.setStatus((byte)2);
            taskUserDao.updateByPrimaryKey(taskUser);
        }
        return users;
    }

    @Override
    public boolean addTaskUser(TaskUser taskUser) {
        return taskUserDao.insertSelective(taskUser) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editTaskUser(TaskUser taskUser) {
        return taskUserDao.updateByPrimaryKeySelective(taskUser)==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delTaskUser(long id) {
        return taskUserDao.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public TaskUser getTaskUserById(long id) {
        return taskUserDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertTaskUserList(List<TaskUser> list) {
        return taskUserDao.insertBatch(list) == list.size() ? Boolean.TRUE :Boolean.FALSE;
    }

    @Override
    public TaskUser[] taskUserList(String user_id ,String task_id) {
        return taskUserDao.getAllTaskUsers(user_id ,Integer.parseInt(task_id));
    }

    @Override
    public List<String> getTaskUserCount(String userId, String staTime, String endTime) {
        if(StringUtils.isEmpty(staTime)||StringUtils.isEmpty(endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar now = new GregorianCalendar();
            Date d = new Date();
            endTime= sdf.format(d) +" 00:00:00";
            now.setTime(d);
            now.set(Calendar.DATE, now.get(Calendar.DATE) - 7);
            staTime = sdf.format(now.getTime()) +" 23:59:59";;
        }
        return taskUserDao.getCountTaskUser(userId,staTime,endTime);
    }

    @Override
    public int getTaskUserCount(String userId) {
        return taskUserDao.getCountTaskUserToDo(userId);
    }

    @Override
    public List<String> getUserType( String taskId) {
        return taskUserDao.countUserTypeByTaskId(taskId );
    }
}
