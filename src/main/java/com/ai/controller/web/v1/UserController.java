package com.ai.controller.web.v1;

import com.ai.domain.vo.Account;
import com.ai.service.AccountService;
import com.ai.util.JsonWebTokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.vo.Message;
import com.ai.service.UserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* *
 * @Author tomsun28
 * @Description 用户相关操作
 * @Date 21:05 2018/3/17
 */
@RestController
@RequestMapping("/web/api/v1/user")
public class UserController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;


    @ApiOperation(value = "获取对应用户角色",notes = "GET根据用户的appId获取对应用户的角色")
    @GetMapping("/role/{appId}")
    public Message getUserRoleList(@PathVariable String appId) {

        String roles = userService.loadAccountRole(appId);
        Set<String> roleSet = JsonWebTokenUtil.split(roles);
        LOGGER.info(roleSet.toString());
        return new Message().ok(6666,"return roles success").addData("roles",roleSet);
    }


    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户列表",notes = "GET获取所有注册用户的信息列表")
    @GetMapping("/list/{start}/{limit}")
    public Message getUserList(@PathVariable Integer start, @PathVariable Integer limit) {

        PageHelper.startPage(start,limit);
        List<AuthUser> authUsers = userService.getUserList();
        authUsers.forEach(user->user.setPassword(null));
        PageInfo pageInfo = new PageInfo(authUsers);
        return new Message().ok(6666,"return user list success").addData("pageInfo",pageInfo);
    }

    @ApiOperation(value = "给用户授权添加角色",httpMethod = "POST")
    @PostMapping("/authority/role")
    public Message authorityUserRole(HttpServletRequest request) {
        Map<String,String> map = getRequestBody(request);
        String uid = map.get("uid");
        int roleId = Integer.valueOf(String.valueOf(map.get("roleId")));
        boolean flag = userService.authorityUserRole(uid,roleId);
        return flag ? new Message().ok(6666,"authority success") : new Message().error(1111,"authority error");
    }

    @ApiOperation(value = "删除已经授权的用户角色",httpMethod = "DELETE")
    @DeleteMapping("/authority/role/{uid}/{roleId}")
    public Message deleteAuthorityUserRole(@PathVariable String uid, @PathVariable Integer roleId) {
        return userService.deleteAuthorityUserRole(uid,roleId) ? new Message().ok(6666,"delete success") : new Message().error(1111,"delete fail");
    }


    @ApiOperation(value = "用户登出", httpMethod = "POST")
    @PostMapping("/exit")
    public Message accountExit(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        Map<String,String > map = getRequestHeader(request);
        String appId = map.get("appId");
        if (StringUtils.isEmpty(appId)) {
            return new Message().error(1111, "用户未登录无法登出");
        }
        String jwt = redisTemplate.opsForValue().get("JWT-SESSION-"+appId);
        if (StringUtils.isEmpty(jwt)) {
            return new Message().error(1111, "用户未登录无法登出");
        }
        redisTemplate.opsForValue().getOperations().delete("JWT-SESSION-"+appId);
        LogExeManager.getInstance().executeLogTask(LogTaskFactory.exitLog(appId,request.getRemoteAddr(),(short)1,""));

        return new Message().ok(6666, "用户退出成功");
    }

    @ApiOperation(value = "获取用户信息", notes = "包含用户信息和权限信息")
    @GetMapping("/info")
    public Message info(HttpServletRequest request, HttpServletResponse response) {
        String appId =request.getHeader("appId");

        Account account = accountService.loadAccount(appId);
        String roles = userService.loadAccountRole(appId);
        System.out.println("===============================================");
        System.out.println("||||||||||||||||||||||||||||"+appId);
        System.out.println(account);
        System.out.println(roles);
        System.out.println("=================================================");
        return new Message().ok(1009, "获取用户信息成功").addData("account",account).addData("roles",roles.split(","));
    }

}
