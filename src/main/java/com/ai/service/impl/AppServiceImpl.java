package com.ai.service.impl;

import com.ai.dao.AppDao;
import com.ai.dao.AppMapper;
import com.ai.domain.bo.App;
import com.ai.service.AppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("AppService")
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    @Autowired
    private AppMapper appMapper;
    @Override
    public PageInfo<App> findAllApp(int pageNum, int pageSize,String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<App> appsList = appDao.getAppsList(name);
        PageInfo result = new PageInfo(appsList);
        return result;
    }

    @Override
    public boolean registerApp(App app) {
        // 新增app
        return appMapper.insert(app) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isAppExistByName(String name) {
        App app = appDao.selectByName(name);
        return app != null ? Boolean.TRUE : Boolean.FALSE;
    }
    @Override
    public boolean isAppExistByName(String name,String id) {
        App app = appDao.selectByName(name);
        if(app.getId().toString().equals(id)){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public boolean editApp(App app) {
        return appMapper.updateByPrimaryKeySelective(app) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delApp(long id) {
        return appMapper.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public App getAppById(long id) {
        return appMapper.selectByPrimaryKey(id);
    }
}
