package com.ai.controller.web.v1;

import com.ai.domain.bo.AuthRole;
import com.ai.domain.bo.AuthUserRole;
import com.ai.service.RoleService;
import com.ai.util.*;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.vo.Message;
import com.ai.service.AccountService;
import com.ai.service.UserService;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* *
 * @Author tomsun28
 * @Description post新增,get读取,put完整更新,patch部分更新,delete删除
 * @Date 14:40 2018/3/8
 */
@RestController
@RequestMapping("/web/api/v1/account")
public class AccountController extends BasicAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /* *
     * @Description 这里已经在 passwordFilter 进行了登录认证
     * @Param [] 登录签发 JWT
     * @Return java.lang.String
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    public Message accountLogin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String appId = params.get("appId");
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accountService.loadAccountRole(appId);
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        long refreshPeriodTime = 36000L;
        String jwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(), appId,
                "token-server", refreshPeriodTime >> 2, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set("JWT-SESSION-" + appId, jwt, refreshPeriodTime, TimeUnit.SECONDS);
        AuthUser authUser = userService.getUserByAppId(appId);
        authUser.setPassword(null);
        authUser.setSalt(null);

        LogExeManager.getInstance().executeLogTask(LogTaskFactory.loginLog(appId, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "登录成功"));

        return new Message().ok(1003, "issue jwt success").addData("jwt", jwt).addData("user", authUser);
    }

    /* *
     * @Description 用户账号的注册
     * @Param [request, response]
     * @Return com.ai.domain.vo.Message
     *//*
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public Message accountRegister(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        AuthUser authUser = new AuthUser();
        String uid = params.get("uid");
        String password = params.get("password");
        String userKey = params.get("userKey");
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(password)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(1111, "账户信息缺失");
        }
        if (accountService.isAccountExistByUid(uid)) {
            // 账户已存在
            return new Message().error(1111, "账户已存在");
        }

        authUser.setUid(uid);

        // 从Redis取出密码传输加密解密秘钥
        String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey);
        String realPassword = AESUtil.aesDecode(password, tokenKey);
        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        authUser.setPassword(MD5Util.md5(realPassword + salt));
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

        if (accountService.registerAccount(authUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
            return new Message().ok(2002, "注册成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return new Message().ok(1111, "注册失败");
        }
    }*/

    /* *
     * @Description 用户账号的注册
     * @Param [request, response]
     * @Return com.ai.domain.vo.Message
     */
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public Message accountRegister(HttpServletRequest request, HttpServletResponse response) {

        String appId = request.getHeader("appId");
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        AuthUser authUser = new AuthUser();
        String uid = params.get("uid");
        String roleId = params.get("roleId");
        String password = params.get("password");
        String userKey = params.get("userKey");
        boolean flag = false;
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(password)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(1111, "账户信息缺失");
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{4,30}");
        Matcher matcher = pattern.matcher(uid);
        boolean b= matcher.matches();
        if(!b){
            return new Message().error(1011, "用户名只允许字母和数字，长度在4到30之间");
        }

        if (accountService.isAccountExistByUid(uid)) {
            // 账户已存在
            return new Message().error(1111, "账户已存在");
        }
        authUser.setUid(uid);

        // 从Redis取出密码传输加密解密秘钥
        //String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey);
        //测试使用，在请求时 将tokenKey放入body中
        String tokenKey = params.get("tokenKey");

        String realPassword = AESUtil.aesDecode(password, tokenKey);
        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        authUser.setPassword(MD5Util.md5(realPassword + salt));
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


        //获取当前角色的权限信息
        String authRoleId = accountService.loadAccountRoleId(appId);
        List<AuthRole> roleList = roleService.getRolesById(Integer.parseInt(authRoleId));
        if(roleList ==null){
            return new Message().ok(1009, "注册失败");
        }
        //判断值是否存在
        for (int i = 0; i < roleList.size(); i++){
            if (Integer.parseInt(roleId) == roleList.get(i).getId()){
                flag = true;
            }
        }
        if(flag){
            //插入用户表
            if (accountService.registerAccount(authUser)) {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
                //return new Message().ok(2002, "注册成功");
            } else {
                LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
                return new Message().ok(1111, "注册失败");
            }
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
        }else{
            //返回错误信息
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return new Message().ok(1009, "注册失败");
        }
    }

}
