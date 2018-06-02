package com.ai.dao;

import com.ai.domain.bo.UserSchedule;

public interface UserScheduleMapper {
    int insert(UserSchedule record);

    int insertSelective(UserSchedule record);
}