package com.ai.dao;

import com.ai.domain.bo.SipUser;
import com.ai.domain.bo.SipUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SipUserMapper {
    long countByExample(SipUserExample example);

    int deleteByExample(SipUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SipUser record);

    int insertSelective(SipUser record);

    List<SipUser> selectByExample(SipUserExample example);

    SipUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SipUser record, @Param("example") SipUserExample example);

    int updateByExample(@Param("record") SipUser record, @Param("example") SipUserExample example);

    int updateByPrimaryKeySelective(SipUser record);

    int updateByPrimaryKey(SipUser record);
}