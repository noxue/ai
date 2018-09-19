package com.ai.dao;

import com.ai.domain.bo.Sip;
import com.ai.domain.bo.SipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SipMapper {
    long countByExample(SipExample example);

    int deleteByExample(SipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sip record);

    int insertSelective(Sip record);

    List<Sip> selectByExample(SipExample example);

    Sip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sip record, @Param("example") SipExample example);

    int updateByExample(@Param("record") Sip record, @Param("example") SipExample example);

    int updateByPrimaryKeySelective(Sip record);

    int updateByPrimaryKey(Sip record);
}