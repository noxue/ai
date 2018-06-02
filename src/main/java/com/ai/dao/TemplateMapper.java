package com.ai.dao;

import com.ai.domain.bo.Template;
import com.ai.domain.bo.TemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateMapper {
    long countByExample(TemplateExample example);

    int deleteByExample(TemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    int insertSelective(Template record);

    List<Template> selectByExampleWithBLOBs(TemplateExample example);

    List<Template> selectByExample(TemplateExample example);

    Template selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKeyWithBLOBs(Template record);

    int updateByPrimaryKey(Template record);
}