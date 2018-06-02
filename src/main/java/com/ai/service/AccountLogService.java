package com.ai.service;

import com.ai.domain.bo.AuthAccountLog;

import java.util.List;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 9:30 2018/4/22
 */
public interface AccountLogService {

    List<AuthAccountLog> getAccountLogList();
}
