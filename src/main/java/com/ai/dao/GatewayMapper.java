package com.ai.dao;

import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.GatewayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GatewayMapper {
    long countByExample(GatewayExample example);

    int deleteByExample(GatewayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Gateway record);

    int insertSelective(Gateway record);

    List<Gateway> selectByExample(GatewayExample example);

    Gateway selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Gateway record, @Param("example") GatewayExample example);

    int updateByExample(@Param("record") Gateway record, @Param("example") GatewayExample example);

    int updateByPrimaryKeySelective(Gateway record);

    int updateByPrimaryKey(Gateway record);
}