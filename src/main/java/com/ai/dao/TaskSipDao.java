package com.ai.dao;

import com.ai.domain.bo.TaskSip;

import java.util.List;

public interface TaskSipDao extends TaskSipMapper{

    int insertBatch(List<TaskSip> list);

}