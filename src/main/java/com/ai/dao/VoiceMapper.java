package com.ai.dao;

import com.ai.domain.bo.Voice;

public interface VoiceMapper {
    int deleteByPrimaryKey(String hash);

    int insert(Voice record);

    int insertSelective(Voice record);

    Voice selectByPrimaryKey(String hash);

    int updateByPrimaryKeySelective(Voice record);

    int updateByPrimaryKey(Voice record);
}