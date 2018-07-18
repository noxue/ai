package com.ai.dao;

import com.ai.domain.bo.SimUser;
import com.ai.domain.bo.SimUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SimUserMapper {
    long countByExample(SimUserExample example);

    int deleteByExample(SimUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SimUser record);

    int insertSelective(SimUser record);

    List<SimUser> selectByExample(SimUserExample example);

    SimUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SimUser record, @Param("example") SimUserExample example);

    int updateByExample(@Param("record") SimUser record, @Param("example") SimUserExample example);

    int updateByPrimaryKeySelective(SimUser record);

    int updateByPrimaryKey(SimUser record);
}