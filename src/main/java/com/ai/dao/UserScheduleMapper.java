package com.ai.dao;

import com.ai.domain.bo.UserSchedule;
import com.ai.domain.bo.UserScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserScheduleMapper {
    long countByExample(UserScheduleExample example);

    int deleteByExample(UserScheduleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserSchedule record);

    int insertSelective(UserSchedule record);

    List<UserSchedule> selectByExample(UserScheduleExample example);

    UserSchedule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserSchedule record, @Param("example") UserScheduleExample example);

    int updateByExample(@Param("record") UserSchedule record, @Param("example") UserScheduleExample example);

    int updateByPrimaryKeySelective(UserSchedule record);

    int updateByPrimaryKey(UserSchedule record);
}