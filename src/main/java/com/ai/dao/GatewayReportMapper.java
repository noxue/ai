package com.ai.dao;

import com.ai.domain.bo.GatewayReport;
import com.ai.domain.bo.GatewayReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GatewayReportMapper {
    long countByExample(GatewayReportExample example);

    int deleteByExample(GatewayReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GatewayReport record);

    int insertSelective(GatewayReport record);

    List<GatewayReport> selectByExampleWithBLOBs(GatewayReportExample example);

    List<GatewayReport> selectByExample(GatewayReportExample example);

    GatewayReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GatewayReport record, @Param("example") GatewayReportExample example);

    int updateByExampleWithBLOBs(@Param("record") GatewayReport record, @Param("example") GatewayReportExample example);

    int updateByExample(@Param("record") GatewayReport record, @Param("example") GatewayReportExample example);

    int updateByPrimaryKeySelective(GatewayReport record);

    int updateByPrimaryKeyWithBLOBs(GatewayReport record);

    int updateByPrimaryKey(GatewayReport record);
}