package com.ai.shiro.filter;

import com.ai.domain.bo.App;
import com.ai.domain.vo.Message;
import com.ai.service.AppService;
import com.ai.util.RequestResponseUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AppFilter implements Filter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AppService appService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String appid = httpRequest.getHeader("appid");
        String key = httpRequest.getHeader("key");
        // String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if(appid.equals("")|| key.length()!=16) {
            Message message = new Message().error(-1,"error request");
            RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
            return;
        }

        String robot_key = redisTemplate.opsForValue().get("robot_"+appid);
        if (robot_key==null) {
            App app = appService.getAppById(Long.parseLong(appid));
            if(app != null){
                robot_key = app.getKey();
                redisTemplate.opsForValue().set("robot_"+ appid, key);
            }
        }

        if (robot_key.equals(key)) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            Message message = new Message().error(-1,"error request");
            RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
        }


        //根据网关信息获取sim卡信息

        //根据sim_id获取他需要执行的任务列表

        // 根据任务编号(tas_kid)从task_user表中获取5个任务
    }

    @Override
    public void destroy() {

    }

}