package com.ai.service;

import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskSip;

import java.util.List;

public interface TaskSipService {

    boolean insertList(List<TaskSip> taskSip);
    List<Task> getTasksBySip(long id);
}
