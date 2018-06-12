package com.ai.service;

import com.ai.domain.bo.Task;
import com.github.pagehelper.PageInfo;

public interface TaskService {

    PageInfo<Task> findAllTaskByAppId(int pageNum, int pageSize, String appId);

    boolean registerTask(Task task);

    boolean editTask(Task task);

    boolean delTask(long id);

    Task getTaskById(long id);

}
