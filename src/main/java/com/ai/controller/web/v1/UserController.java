package com.ai.controller.web.v1;

import com.ai.domain.bo.AuthRole;
import com.ai.domain.bo.AuthUserRole;
import com.ai.domain.vo.Account;
import com.ai.service.AccountService;
import com.ai.service.RoleService;
import com.ai.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.vo.Message;
import com.ai.service.UserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private RoleService roleService;


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

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户列表",notes = "POST获取所有注册用户的信息列表")
    @PostMapping("/list")
    public Message getUsersList(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String st = params.get("start");
        int start = Integer.parseInt(st);
        int limit = 15;
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        String roleId= accountService.loadAccountRoleId(uid);
        if(roleId.equals("100")){
            uid = "";
        }
        PageHelper.startPage(start,limit);
        List<AuthUser> authUsers = userService.getUserList(uid);
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

    /* *
     * @Description 用户账号的注册
     * @Param [request, response]
     * @Return com.ai.domain.vo.Message
     */
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/add")
    public Message addAccount(HttpServletRequest request, HttpServletResponse response) {

        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String roleId = params.get("roleId");
        boolean flag = false;
        // 获取当前用户能创建那些角色的用户
        String authRoleId = accountService.loadAccountRoleId(appId);
        List<AuthRole> roleList = roleService.getRolesById(Integer.parseInt(authRoleId));
        if(roleList ==null){
            return new Message().ok(1009, "当前角色无法创建用户");
        }
        //判断是否能创建这类角色的用户
        for (int i = 0; i < roleList.size(); i++){
            if (Integer.parseInt(roleId) == roleList.get(i).getId()){
                flag = true;
            }
        }
        AuthUser authUser = new AuthUser();
        String uid = params.get("uid");
        String password = params.get("password");
        //String userKey = params.get("userKey");
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(password)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(1111, "账户信息缺失");
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{5,30}");
        Matcher matcher = pattern.matcher(uid);
        boolean b= matcher.matches();
        if(!b){
            return new Message().error(1011, "用户名只允许字母和数字，长度在5到30之间");
        }
        if(password.length()<5||password.length()>30){
            return new Message().error(1011, "请控制密码长度在5到30之间");
        }
        if (accountService.isAccountExistByUid(uid)) {
            // 账户已存在
            return new Message().error(1111, "账户已存在");
        }
        if(flag){
            authUser.setUid(uid);
            // 从Redis取出密码传输加密解密秘钥
            //String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey);
            //测试使用，在请求时 将tokenKey放入body中
            //String tokenKey = params.get("tokenKey");
            //String realPassword = AESUtil.aesDecode(password, tokenKey);
            String salt = CommonUtil.getRandomString(6);
            // 存储到数据库的密码为 MD5(原密码+盐值)
            authUser.setPassword(MD5Util.md5(password + salt));
            authUser.setSalt(salt);
            authUser.setCreateTime(new Date());
            if (!StringUtils.isEmpty(params.get("username"))) {
                authUser.setUsername(params.get("username"));
            }
            if (!StringUtils.isEmpty(params.get("realName"))) {
                authUser.setRealName(params.get("realName"));
            }
            if (!StringUtils.isEmpty(params.get("avatar"))) {
                authUser.setAvatar(params.get("avatar"));
            }
            if (!StringUtils.isEmpty(params.get("phone"))) {
                authUser.setPhone(params.get("phone"));
            }
            if (!StringUtils.isEmpty(params.get("email"))) {
                authUser.setEmail(params.get("email"));
            }
            if (!StringUtils.isEmpty(params.get("sex"))) {
                authUser.setSex(Byte.valueOf(params.get("sex")));
            }
            if (!StringUtils.isEmpty(params.get("createWhere"))) {
                authUser.setCreateWhere(Byte.valueOf(params.get("createWhere")));
            }
            authUser.setStatus((byte) 1);
            authUser.setPid(appId);
            //插入用户表
            if (accountService.registerAccount(authUser)) {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
                //插入表auth_user_role
                AuthUserRole aur = new AuthUserRole();
                aur.setRoleId(Integer.parseInt(roleId));
                aur.setUserId(uid);
                aur.setCreateTime(new Date());
                if (accountService.insertAuthUserRole(aur)){
                    LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
                    return new Message().ok(2002, "注册成功");
                }else{
                    LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
                    return new Message().ok(1010, "注册失败");
                }
            } else {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
                return new Message().ok(1111, "注册失败");
            }
        }else{
            //返回错误信息
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return new Message().ok(1009, "注册失败");
        }
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/roleList")
    public Message roleList(HttpServletRequest request, HttpServletResponse response) {
        String appId = request.getHeader("appId");
        String authRoleId = accountService.loadAccountRoleId(appId);
        List<AuthRole> roleList = roleService.getRolesById(Integer.parseInt(authRoleId));
        if(roleList!=null){
            return new Message().ok(1112, "获取用户信息成功").addData("roleList",roleList);
        }else {
            return new Message().error(1113, "注册失败");
        }
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping("/edit")
    public Message editAccount(HttpServletRequest request, HttpServletResponse response) {

        String appId = request.getHeader("appId");
        boolean flag = false;
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        AuthUser oldAuthUser = new AuthUser();
        String uid = params.get("uid");
        oldAuthUser = userService.getUserByAppId(uid);
        if( appId.equals("admin")|| appId.equals(uid)|| appId.equals(oldAuthUser.getPid()) ){
            flag = true;
        }else{
            return new Message().error(1111, "当前用户无权操作");
        }
        //执行更新操作
        if(flag){
            String password = params.get("password");
            String salt = CommonUtil.getRandomString(6);
            AuthUser authUser = new AuthUser();
            authUser.setUid(uid);
            // 存储到数据库的密码为 MD5(原密码+盐值)
            if(!"".equals(password)){
                Pattern pattern = Pattern.compile("[a-zA-Z0-9]{5,30}");
                Matcher matcher = pattern.matcher(uid);
                boolean b= matcher.matches();
                if(!b){
                    return new Message().error(1011, "用户名只允许字母和数字，长度在5到30之间");
                }
                if(password.length()<5||password.length()>30){
                    return new Message().error(1011, "请控制密码长度在5到30之间");
                }
                authUser.setPassword(MD5Util.md5(password + salt));
                authUser.setSalt(salt);
            }
            authUser.setUpdateTime(new Date());
            if (!StringUtils.isEmpty(params.get("username"))) {
                authUser.setUsername(params.get("username"));
            }
            if (!StringUtils.isEmpty(params.get("realName"))) {
                authUser.setRealName(params.get("realName"));
            }
            if (!StringUtils.isEmpty(params.get("avatar"))) {
                authUser.setAvatar(params.get("avatar"));
            }
            if (!StringUtils.isEmpty(params.get("phone"))) {
                authUser.setPhone(params.get("phone"));
            }
            if (!StringUtils.isEmpty(params.get("email"))) {
                authUser.setEmail(params.get("email"));
            }
            if (!StringUtils.isEmpty(params.get("sex")) && !"null".equals(params.get("sex"))) {
                authUser.setSex(Byte.valueOf(params.get("sex")));
            }
            if (!StringUtils.isEmpty(params.get("createWhere"))) {
                authUser.setCreateWhere(Byte.valueOf(params.get("createWhere")));
            }
            authUser.setStatus((byte) 1);
            //插入用户表
            if (accountService.editAuthUser(authUser)) {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "编辑成功"));
                return new Message().ok(1010, "编辑成功");
            } else {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "编辑失败"));
                return new Message().error(1111, "编辑失败");
            }

        }else{
            //返回错误信息
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "编辑失败"));
            return new Message().error(1009, "编辑失败");
        }
    }

    @ApiOperation(value = "删除用户", notes = "删除用户以及角色信息")
    @PostMapping("/del")
    public Message delUser(HttpServletRequest request, HttpServletResponse response) {
        String appId = request.getHeader("appId");
        if(StringUtils.isEmpty(appId)){
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String uid = params.get("uid");
        if(StringUtils.isEmpty(uid)){
            return new Message().error(1114, "信息缺失");
        }
        //判断当前用户是否可以进行删除操作
        if(accountService.getUserByUidAndPid(uid,appId) == null){
            return new Message().error(2004, "无法进行删除操作");
        }

        if(userService.delUser(uid)){
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "删除成功"));
            if(userService.deleteAuthRoleUserByUserId(uid)){
                return new Message().ok(1115, "删除用户信息成功");
            }else{
                return new Message().error(1113, "删除失败");
            }
        }else {
            return new Message().error(1113, "删除失败");
        }
    }


}
