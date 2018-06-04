package com.ai.service;

import com.ai.domain.bo.App;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppService {

    PageInfo<App> findAllApp(int pageNum, int pageSize);

    boolean registerApp(App app);

    boolean isAppExistByName(String name);

    boolean editApp(App app);

    boolean delApp(long id);

}
