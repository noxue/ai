package com.ai.dao;

import com.ai.domain.bo.TaskSip;
import com.ai.domain.bo.TaskSipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskSipMapper {
    long countByExample(TaskSipExample example);

    int deleteByExample(TaskSipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskSip record);

    int insertSelective(TaskSip record);

    List<TaskSip> selectByExample(TaskSipExample example);

    TaskSip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskSip record, @Param("example") TaskSipExample example);

    int updateByExample(@Param("record") TaskSip record, @Param("example") TaskSipExample example);

    int updateByPrimaryKeySelective(TaskSip record);

    int updateByPrimaryKey(TaskSip record);
}