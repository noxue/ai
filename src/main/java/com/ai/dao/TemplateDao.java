package com.ai.dao;

import com.ai.domain.bo.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateDao extends TemplateMapper{

    //根据uid查询模板信息
    List<Template> getTemplateListByUid(@Param("userId") String uid ,@Param("name")  String name);

    //根据uid查询authUser表中的pid，再查询模板信息
    List<Template> getTemplateListByPid(@Param("uid")String uid ,@Param("name")  String name);

    Template selectByName(String name);

}