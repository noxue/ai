package com.ai.dao;

import com.ai.domain.bo.TaskUserReport;

public interface TaskUserReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskUserReport record);

    int insertSelective(TaskUserReport record);

    TaskUserReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskUserReport record);

    int updateByPrimaryKeyWithBLOBs(TaskUserReport record);

    int updateByPrimaryKey(TaskUserReport record);
}