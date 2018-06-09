package com.ai.service.impl;

import com.ai.dao.AuthRoleMapper;
import com.ai.dao.TemplateDao;
import com.ai.domain.bo.AuthRole;
import com.ai.domain.bo.AuthUserRole;
import com.ai.domain.bo.Template;
import com.ai.service.AccountService;
import com.ai.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TemplateService")
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Override
    public PageInfo<Template> findAllTemlate(int pageNum, int pageSize, String uid) {
        PageHelper.startPage(pageNum, pageSize);
        boolean flag = false;
        List<Template> teplatesList =null;
        String roleId = accountService.loadAccountRoleId(uid);
        //根据roleid查询该角色是否为企业员工 code : role_user
        AuthRole authRole = authRoleMapper.selectByPrimaryKey(Integer.parseInt(roleId));
        if(authRole.getCode().equals("role_user")){
            teplatesList = templateDao.getTemplateListByPid(uid);
        }else{
            teplatesList = templateDao.getTemplateListByUid(uid);
        }
        PageInfo result = new PageInfo(teplatesList);
        return result;
    }

    @Override
    public boolean registerTemplate(Template template) {
        return templateDao.insert(template) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isTemplateExistByName(String name) {
        Template template = templateDao.selectByName(name);
        return template != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editTemplate(Template template) {
        return templateDao.updateByPrimaryKeyWithBLOBs(template)  ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delTemplate(long id) {
        return templateDao.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Template getTemplateById(long id) {
        return templateDao.selectByPrimaryKey(id);
    }
}
