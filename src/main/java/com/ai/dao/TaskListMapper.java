package com.ai.dao;

import com.ai.domain.bo.TaskList;

public interface TaskListMapper {
    int insert(TaskList record);

    int insertSelective(TaskList record);
}