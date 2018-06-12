package com.ai.service.impl;

import com.ai.dao.TaskUserReportMapper;
import com.ai.domain.bo.TaskUserReport;
import com.ai.service.TaskUserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TaskUserReportService")
public class TaskUserReportServiceImpl implements TaskUserReportService {

    @Autowired
    private TaskUserReportMapper taskUserReportMapper;

    @Override
    public boolean addTaskUserReport(TaskUserReport taskUserReport) {
        return taskUserReportMapper.insert(taskUserReport)==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public TaskUserReport getTaskUserReportById(long id) {
        return taskUserReportMapper.selectByPrimaryKey(id);
    }
}
