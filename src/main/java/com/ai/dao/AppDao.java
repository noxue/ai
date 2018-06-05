package com.ai.dao;

import com.ai.domain.bo.App;
import org.apache.catalina.User;

import java.util.List;

public interface AppDao extends AppMapper{

    //获取所有app
    List<App> getAppsList(String name);
    //根据name查找app
    App selectByName(String name);

}