package com.ai.service;

import com.ai.domain.bo.App;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppService {

    PageInfo<App> findAllApp(int pageNum, int pageSize,String name);

    boolean registerApp(App app);

    boolean isAppExistByName(String name);

    boolean isAppExistByName(String name,String id);

    boolean editApp(App app);

    boolean delApp(long id);

    App getAppById(long id);

}
