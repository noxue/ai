package com.ai.dao;

import com.ai.domain.bo.Voice;

public interface VoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Voice record);

    int insertSelective(Voice record);

    Voice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Voice record);

    int updateByPrimaryKey(Voice record);
}