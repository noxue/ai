package com.ai.service;

import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskUserService {

    PageInfo<TaskUser> findAllTaskUser(int pageNum, int pageSize,String userId, String taskId, String name, String type, String share,String status);

    List<TaskUser> selectTaskUserByTaskId(int id);

    List<TaskUser> getTaskUserAndUpdate(long taskId);

    boolean addTaskUser(TaskUser taskUser);

    boolean editTaskUser(TaskUser taskUser);

    boolean delTaskUser(long id);

    TaskUser getTaskUserById(long id);

    //批量插入
    boolean insertTaskUserList (List<TaskUser> list);
    //导出
    TaskUser[] taskUserList(String appId,String task_id);


}
