package com.ai.service;

import com.ai.domain.bo.TaskUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskUserService {

    PageInfo<TaskUser> findAllTaskUser(int pageNum, int pageSize, String user_id, String test, String type, String share);

    boolean addTaskUser(TaskUser taskUser);

    boolean editTaskUser(TaskUser taskUser);

    boolean delTaskUser(long id);

    TaskUser getTaskUserById(long id);

    //批量插入
    boolean insertTaskUserList (List<TaskUser> list);
}
