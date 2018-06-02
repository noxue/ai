package com.ai.dao;

import com.ai.domain.bo.SimUser;
import com.ai.domain.bo.SimUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SimUserMapper {
    long countByExample(SimUserExample example);

    int deleteByExample(SimUserExample example);

    int insert(SimUser record);

    int insertSelective(SimUser record);

    List<SimUser> selectByExample(SimUserExample example);

    int updateByExampleSelective(@Param("record") SimUser record, @Param("example") SimUserExample example);

    int updateByExample(@Param("record") SimUser record, @Param("example") SimUserExample example);
}