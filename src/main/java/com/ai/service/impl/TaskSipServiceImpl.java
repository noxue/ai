package com.ai.service.impl;

import com.ai.dao.TaskDao;
import com.ai.dao.TaskSipDao;
import com.ai.domain.bo.Task;
import com.ai.domain.bo.TaskExample;
import com.ai.domain.bo.TaskSip;
import com.ai.domain.bo.TaskSipExample;
import com.ai.service.TaskSipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("TaskSipService")
public class TaskSipServiceImpl implements TaskSipService {

    @Autowired
    private TaskSipDao taskSipDao;

    @Autowired
    private TaskDao taskDao;

    @Override
    public boolean insertList(List<TaskSip> taskSipList) {
        int  i = taskSipDao.insertBatch(taskSipList);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Task> getTasksBySip(long id) {
        TaskSipExample ex = new TaskSipExample();
        TaskSipExample.Criteria cr = ex.createCriteria();
        cr.andSipIdEqualTo(id);
        List<TaskSip> taskSips = taskSipDao.selectByExample(ex);

        List<Long> ids = new ArrayList<>();
        for (TaskSip taskSip:taskSips){
            ids.add(taskSip.getTaskId());
        }

        TaskExample tex = new TaskExample();
        TaskExample.Criteria tcr = tex.createCriteria();
        tcr.andIdIn(ids);
        return taskDao.selectByExample(tex);
    }


}
