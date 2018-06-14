package com.ai.dao;

import com.ai.domain.bo.SimUser;

import java.util.List;

public interface SimUserDao extends SimUserMapper{

   SimUser getSimUserBySimIdAndUserId(String user_id , int sim_id);

}