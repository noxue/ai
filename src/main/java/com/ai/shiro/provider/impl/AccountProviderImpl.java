package com.ai.shiro.provider.impl;


import com.ai.domain.vo.Account;
import com.ai.service.AccountService;
import com.ai.shiro.provider.AccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 9:22 2018/2/13
 */
@Service("AccountProvider")
public class AccountProviderImpl implements AccountProvider {

      @Autowired
      @Qualifier("AccountService")
      private AccountService accountService;

    public Account loadAccount(String appId) {
        return accountService.loadAccount(appId);
    }
}
