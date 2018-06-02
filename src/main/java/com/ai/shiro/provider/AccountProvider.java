package com.ai.shiro.provider;


import com.ai.domain.vo.Account;

/* *
 * @Author tomsun28
 * @Description  数据库用户密码账户提供
 * @Date 16:35 2018/2/11
 */
public interface AccountProvider {

    Account loadAccount(String appId);

}
