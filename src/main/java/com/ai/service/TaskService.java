package com.ai.service;

import com.ai.domain.bo.Task;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TaskService {

    PageInfo<Task> findAllTaskByAppId(int pageNum, int pageSize, String appId, String name);

    List<Task> findTaskByUserId(List<String> userIds);

    List<Task> getStartedTasksByUserId(List<String> userIds);

    List<Task> getTasksWithStatus(int status);

    boolean registerTask(Task task);

    boolean editTask(Task task);

    boolean delTask(long id);

    Task getTaskById(long id);

    int getTaskCount(String userId);

}
