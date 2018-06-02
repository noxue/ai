package com.ai.dao;

import com.ai.domain.bo.TaskList;
import com.ai.domain.bo.TaskListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskListMapper {
    long countByExample(TaskListExample example);

    int deleteByExample(TaskListExample example);

    int insert(TaskList record);

    int insertSelective(TaskList record);

    List<TaskList> selectByExampleWithBLOBs(TaskListExample example);

    List<TaskList> selectByExample(TaskListExample example);

    int updateByExampleSelective(@Param("record") TaskList record, @Param("example") TaskListExample example);

    int updateByExampleWithBLOBs(@Param("record") TaskList record, @Param("example") TaskListExample example);

    int updateByExample(@Param("record") TaskList record, @Param("example") TaskListExample example);
}