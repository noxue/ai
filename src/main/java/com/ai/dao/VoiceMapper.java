package com.ai.dao;

import com.ai.domain.bo.Voice;
import com.ai.domain.bo.VoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoiceMapper {
    long countByExample(VoiceExample example);

    int deleteByExample(VoiceExample example);

    int deleteByPrimaryKey(String hash);

    int insert(Voice record);

    int insertSelective(Voice record);

    List<Voice> selectByExample(VoiceExample example);

    Voice selectByPrimaryKey(String hash);

    int updateByExampleSelective(@Param("record") Voice record, @Param("example") VoiceExample example);

    int updateByExample(@Param("record") Voice record, @Param("example") VoiceExample example);

    int updateByPrimaryKeySelective(Voice record);

    int updateByPrimaryKey(Voice record);
}