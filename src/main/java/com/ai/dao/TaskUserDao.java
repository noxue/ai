package com.ai.dao;

import com.ai.domain.bo.TaskUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskUserDao extends TaskUserMapper{

    //根据taskId,test,type,share获取TaskUser
    List<TaskUser> selectTaskUserListByConditions(@Param("userId") String userId, @Param("taskId") String taskId,
                                                  @Param("name") String name,  @Param("type") String type,
                                                  @Param("share") String share,@Param("status") String status);

    //根据taskId,test,type,share获取TaskUser  导出2.0
    List<TaskUser> getTaskUserListByConditions(@Param("userId") String userId, @Param("taskId") String taskId,
                                                  @Param("name") String name,  @Param("type") String type,
                                                  @Param("share") String share,@Param("status") String status);

    //批量插入
    int insertBatch (List<TaskUser> list);

    //导出1.0
    TaskUser[] getAllTaskUsers(@Param("user_id")String user_id,@Param("task_id") int taskId);

    List<TaskUser> getTaskUserByTaskId(@Param("task_id") int task_id);

    List<String> getCountTaskUser(@Param("userId")String userId ,
                            @Param("staTime")String staTime,@Param("endTime")String endTime);

    int getCountTaskUserToDo(@Param("userId")String userId );

    List<String> countUserTypeByTaskId(@Param("taskId") String taskId);

    List<TaskUser> countToday(@Param("userId")String userId,
                              @Param("staTime")String staTime,@Param("endTime")String endTime);
}