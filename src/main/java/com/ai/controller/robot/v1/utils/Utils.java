package com.ai.controller.robot.v1.utils;

import net.sf.json.JSONObject;

public class Utils {
    public static <T> T Json2Bean(String json, Class<T> tClass){
        JSONObject jsonObject=JSONObject.fromObject(json);
        T t=(T)JSONObject.toBean(jsonObject,tClass);
        return t;
    }
}
