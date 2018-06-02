package com.ai.dao;

import com.ai.domain.bo.Sim;
import com.ai.domain.bo.SimExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SimMapper {
    long countByExample(SimExample example);

    int deleteByExample(SimExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sim record);

    int insertSelective(Sim record);

    List<Sim> selectByExample(SimExample example);

    Sim selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sim record, @Param("example") SimExample example);

    int updateByExample(@Param("record") Sim record, @Param("example") SimExample example);

    int updateByPrimaryKeySelective(Sim record);

    int updateByPrimaryKey(Sim record);
}