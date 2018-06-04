package com.ai.service.impl;

import com.ai.dao.AppDao;
import com.ai.dao.AppMapper;
import com.ai.domain.bo.App;
import com.ai.domain.bo.AuthUser;
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
    public PageInfo<App> findAllApp(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<App> appsList = appDao.getAppsList();
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
}
