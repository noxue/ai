package com.ai.service;

import com.ai.domain.bo.TaskUserReport;

public interface TaskUserReportService {

    boolean addTaskUserReport(TaskUserReport taskUserReport);

    TaskUserReport getTaskUserReportById(long id);

}
