package com.ai.service.impl;

import com.ai.dao.TaskDao;
import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskExample;
import com.ai.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskMapper;

    @Override
    public PageInfo<Task> findAllTaskByAppId(int pageNum, int pageSize, String appId, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Task> tasksList = taskMapper.selectTaskList(appId,name);

        PageInfo result = new PageInfo(tasksList);
        return result;
    }

    @Override
    public List<Task> findTaskByUserId(List<String> userIds) {
        return taskMapper.selectBatch(userIds);
    }

    @Override
    public List<Task> getStartedTasksByUserId(List<String> userIds) {
        return taskMapper.selectStartedByIds(userIds);
    }

    @Override
    public List<Task> getTasksWithStatus(int status) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria cr = example.createCriteria();
        cr.andStatusEqualTo((byte)status);
        return taskMapper.selectByExample(example);
    }

    @Override
    public int getTaskCount(String userId) {
        return taskMapper.getCountTaskToDo(userId);
    }

    @Override
    public boolean registerTask(Task task) {

        return taskMapper.insert(task)  ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editTask(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task) ==1 ? Boolean.TRUE : Boolean.FALSE;
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
