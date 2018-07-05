package com.ai.service.impl;

import com.ai.dao.TaskUserDao;
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

    @Override
    public PageInfo<TaskUser> findAllTaskUser(int pageNum, int pageSize, String taskId,
                                                 String test, String type, String share) {
        PageHelper.startPage(pageNum, pageSize);
        List<TaskUser> taskUserList = taskUserMapper.selectTaskUserListByConditions(taskId,test,type,share);
        PageInfo result = new PageInfo(taskUserList);
        return result;
    }

    @Override
    public boolean addTaskUser(TaskUser taskUser) {
        return taskUserMapper.insertSelective(taskUser) ==1 ? Boolean.TRUE : Boolean.FALSE;
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
        return taskUserMapper.insertBatch(list) == list.size() ? Boolean.TRUE :Boolean.FALSE;
    }

    @Override
    public TaskUser[] taskUserList(String user_id) {
        return taskUserMapper.getAllTaskUsers(user_id);
    }
}
