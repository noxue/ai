package com.ai.dao;

import com.ai.domain.bo.TaskUser;

import java.util.List;

public interface TaskUserDao extends TaskUserMapper{

    //根据user_id,test,type,share获取TaskUser
    List<TaskUser> getTaskUserListByConditions( String user_id, String test, String type, String share);
    //批量插入
    int insertBatch (List<TaskUser> list);

    TaskUser[] getAllTaskUsers(String user_id);
}