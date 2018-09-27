package com.ai.dao;

import com.ai.domain.bo.SipLog;
import com.ai.domain.bo.SipLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SipLogMapper {
    long countByExample(SipLogExample example);

    int deleteByExample(SipLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SipLog record);

    int insertSelective(SipLog record);

    List<SipLog> selectByExample(SipLogExample example);

    SipLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SipLog record, @Param("example") SipLogExample example);

    int updateByExample(@Param("record") SipLog record, @Param("example") SipLogExample example);

    int updateByPrimaryKeySelective(SipLog record);

    int updateByPrimaryKey(SipLog record);
}