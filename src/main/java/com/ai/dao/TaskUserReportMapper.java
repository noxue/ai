package com.ai.dao;

import com.ai.domain.bo.TaskUserReport;
import com.ai.domain.bo.TaskUserReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskUserReportMapper {
    long countByExample(TaskUserReportExample example);

    int deleteByExample(TaskUserReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskUserReport record);

    int insertSelective(TaskUserReport record);

    List<TaskUserReport> selectByExampleWithBLOBs(TaskUserReportExample example);

    List<TaskUserReport> selectByExample(TaskUserReportExample example);

    TaskUserReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskUserReport record, @Param("example") TaskUserReportExample example);

    int updateByExampleWithBLOBs(@Param("record") TaskUserReport record, @Param("example") TaskUserReportExample example);

    int updateByExample(@Param("record") TaskUserReport record, @Param("example") TaskUserReportExample example);

    int updateByPrimaryKeySelective(TaskUserReport record);

    int updateByPrimaryKeyWithBLOBs(TaskUserReport record);

    int updateByPrimaryKey(TaskUserReport record);
}