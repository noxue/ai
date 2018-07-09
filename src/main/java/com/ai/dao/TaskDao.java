package com.ai.dao;

import com.ai.domain.bo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDao extends TaskMapper{

    //根据appId获取所有Task
    List<Task> selectTaskList(@Param("userId") String appId,@Param("name") String name);

    List<Task> selectBatch(List userid);

}