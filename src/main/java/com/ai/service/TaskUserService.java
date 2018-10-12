package com.ai.service;

import com.ai.domain.bo.TaskUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskUserService {

    PageInfo<TaskUser> findAllTaskUser(int pageNum, int pageSize,String userId, String taskId, String name, String type, String share,String status,String date);
    //导出excel 2.0 用于去掉多余的字段
    PageInfo<TaskUser> exportTaskUser (int pageNum, int pageSize,String userId, String taskId, String name, String type, String share,String status,String date);

    List<TaskUser> selectTaskUserByTaskId(int id);

    List<TaskUser> getTaskUserAndUpdate(long taskId);

    boolean addTaskUser(TaskUser taskUser);

    boolean editTaskUser(TaskUser taskUser);

    boolean delTaskUser(long id);

    TaskUser getTaskUserById(long id);

    //批量插入
    boolean insertTaskUserList (List<TaskUser> list);
    //导出 1.0
    TaskUser[] taskUserList(String appId,String task_id);

    List<String> getTaskUserCount(String userId ,String staTime,String endTime);

    int getTaskUserCount(String userId);

    List<String> getUserType(String taskId);

    Object [] getCountToday(String userId);

}
