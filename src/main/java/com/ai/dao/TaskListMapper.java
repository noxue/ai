package com.ai.dao;

public interface TaskListMapper {
    int insert(TaskList record);

    int insertSelective(TaskList record);
}