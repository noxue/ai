package com.ai.dao;

import com.ai.domain.bo.TaskUser;
import com.ai.domain.bo.TaskUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskUserMapper {
    long countByExample(TaskUserExample example);

    int deleteByExample(TaskUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    List<TaskUser> selectByExampleWithBLOBs(TaskUserExample example);

    List<TaskUser> selectByExample(TaskUserExample example);

    TaskUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskUser record, @Param("example") TaskUserExample example);

    int updateByExampleWithBLOBs(@Param("record") TaskUser record, @Param("example") TaskUserExample example);

    int updateByExample(@Param("record") TaskUser record, @Param("example") TaskUserExample example);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKeyWithBLOBs(TaskUser record);

    int updateByPrimaryKey(TaskUser record);
}