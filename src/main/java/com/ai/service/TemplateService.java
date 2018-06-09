package com.ai.service;

import com.ai.domain.bo.Template;
import com.github.pagehelper.PageInfo;

public interface TemplateService {

    PageInfo<Template> findAllTemlate(int pageNum, int pageSize, String uid);

    boolean registerTemplate(Template template);

    boolean isTemplateExistByName(String name);

    boolean editTemplate(Template template);

    boolean delTemplate(long id);

    Template getTemplateById(long id);

}
