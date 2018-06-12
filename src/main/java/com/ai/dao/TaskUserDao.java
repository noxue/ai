package com.ai.dao;

import com.ai.domain.bo.TaskUser;

import java.util.List;

public interface TaskUserDao extends TaskUserMapper{

    //根据user_id,test,type,share获取所有TaskUser
    List<TaskUser> getTaskUserListByConditions( String user_id, String test, String type, String share);

    int insertBatch (List<TaskUser> list);
}