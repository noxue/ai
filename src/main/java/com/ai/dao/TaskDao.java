package com.ai.dao;

import com.ai.domain.bo.Task;

import java.util.List;

public interface TaskDao extends TaskMapper{

    //根据appId获取所有Task
    List<Task> getTasksList(String appId);

}