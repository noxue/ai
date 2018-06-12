package com.ai.service.impl;

import com.ai.dao.TaskUserDao;
import com.ai.dao.TaskUserMapper;
import com.ai.domain.bo.App;
import com.ai.domain.bo.TaskUser;
import com.ai.service.TaskUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskUserService")
public class TaskUserServiceImpl implements TaskUserService {

    @Autowired
    private TaskUserDao taskUserMapper;

    /*@Override
    public PageInfo<App> findAllApp(int pageNum, int pageSize,String name) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<App> appsList = appDao.getAppsList(name);
        PageInfo result = new PageInfo(appsList);
        return result;
    }*/

    @Override
    public PageInfo<TaskUser> findAllTaskUser(int pageNum, int pageSize, String user_id,
                                                 String test, String type, String share) {
        PageHelper.startPage(pageNum, pageSize);
        List<TaskUser> taskUserList = taskUserMapper.getTaskUserListByConditions(user_id,test,type,share);
        PageInfo result = new PageInfo(taskUserList);
        return result;
    }

    @Override
    public boolean addTaskUser(TaskUser taskUser) {
        return taskUserMapper.insert(taskUser) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editTaskUser(TaskUser taskUser) {
        return taskUserMapper.updateByPrimaryKeyWithBLOBs(taskUser)==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delTaskUser(long id) {
        return taskUserMapper.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public TaskUser getTaskUserById(long id) {
        return taskUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertTaskUserList(List<TaskUser> list) {
        return taskUserMapper.insertBatch(list) == 1 ? Boolean.TRUE :Boolean.FALSE;
    }
}
