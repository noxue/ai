package com.ai.dao;

import com.ai.domain.bo.TaskUser;

public interface TaskUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    TaskUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKeyWithBLOBs(TaskUser record);

    int updateByPrimaryKey(TaskUser record);
}