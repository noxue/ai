package com.ai.dao;

import com.ai.domain.bo.UserConfig;
import com.ai.domain.bo.UserConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserConfigMapper {
    long countByExample(UserConfigExample example);

    int deleteByExample(UserConfigExample example);

    int insert(UserConfig record);

    int insertSelective(UserConfig record);

    List<UserConfig> selectByExampleWithBLOBs(UserConfigExample example);

    List<UserConfig> selectByExample(UserConfigExample example);

    int updateByExampleSelective(@Param("record") UserConfig record, @Param("example") UserConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") UserConfig record, @Param("example") UserConfigExample example);

    int updateByExample(@Param("record") UserConfig record, @Param("example") UserConfigExample example);
}