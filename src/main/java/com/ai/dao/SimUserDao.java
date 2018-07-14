package com.ai.dao;

import com.ai.domain.bo.SimUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SimUserDao extends SimUserMapper{

   SimUser getSimUserBySimIdAndUserId(@Param("user_id") String user_id ,@Param("sim_id") long sim_id);

   List<SimUser> getSimUserListBySimId(@Param("sim_id") long sim_id);

   List<SimUser> getListByUserId(@Param("user_id") String user_id);

}