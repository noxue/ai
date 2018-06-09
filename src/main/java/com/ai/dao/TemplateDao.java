package com.ai.dao;

import com.ai.domain.bo.Template;

import java.util.List;

public interface TemplateDao extends TemplateMapper{

    //根据uid查询模板信息
    List<Template> getTemplateListByUid(String uid);

    //根据uid查询authUser表中的pid，再查询模板信息
    List<Template> getTemplateListByPid(String uid);

    Template selectByName(String name);

}