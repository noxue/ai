package com.ai.service.impl;

import com.ai.dao.TaskSipDao;
import com.ai.domain.bo.TaskSip;
import com.ai.service.TaskSipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskSipService")
public class TaskSipServiceImpl implements TaskSipService {

    @Autowired
    private TaskSipDao taskSipDao;

    @Override
    public boolean insertList(List<TaskSip> taskSipList) {
        int  i = taskSipDao.insertBatch(taskSipList);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }
}
