package com.ai.service.impl;

import com.ai.dao.TaskDao;
import com.ai.domain.bo.Task;
import com.ai.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskMapper;

/*
    @Override
    public PageInfo<App> findAllApp(int pageNum, int pageSize,String name) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<App> appsList = appDao.getAppsList(name);
        PageInfo result = new PageInfo(appsList);
        return result;
    }*/

    @Override
    public PageInfo<Task> findAllTaskByAppId(int pageNum, int pageSize, String appId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Task> tasksList = taskMapper.getTasksList(appId);
        PageInfo result = new PageInfo(tasksList);
        return result;
    }

    @Override
    public boolean registerTask(Task task) {

        return taskMapper.insert(task)  ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editTask(Task task) {
        return taskMapper.updateByPrimaryKey(task) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delTask(long id) {
        return taskMapper.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Task getTaskById(long id) {
        return taskMapper.selectByPrimaryKey(id);
    }
}
