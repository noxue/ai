package com.ai.dao;

import com.ai.domain.bo.TaskUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskUserDao extends TaskUserMapper{

    //根据taskId,test,type,share获取TaskUser
    List<TaskUser> selectTaskUserListByConditions(@Param("taskId") String taskId,
            @Param("test") String test,@Param("type") String type,@Param("share") String share);
    //批量插入
    int insertBatch (List<TaskUser> list);

    TaskUser[] getAllTaskUsers(String user_id);
}