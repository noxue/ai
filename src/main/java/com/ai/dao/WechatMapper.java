package com.ai.dao;

import com.ai.domain.bo.Wechat;
import com.ai.domain.bo.WechatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WechatMapper {
    long countByExample(WechatExample example);

    int deleteByExample(WechatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Wechat record);

    int insertSelective(Wechat record);

    List<Wechat> selectByExample(WechatExample example);

    Wechat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Wechat record, @Param("example") WechatExample example);

    int updateByExample(@Param("record") Wechat record, @Param("example") WechatExample example);

    int updateByPrimaryKeySelective(Wechat record);

    int updateByPrimaryKey(Wechat record);
}