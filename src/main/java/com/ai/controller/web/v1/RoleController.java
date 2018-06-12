package com.ai.controller.web.v1;

import com.ai.service.AccountService;
import com.ai.shiro.filter.ShiroFilterChainManager;
import com.ai.support.factory.LogTaskFactory;
import com.ai.support.manager.LogExeManager;
import com.ai.util.IpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ai.domain.bo.AuthResource;
import com.ai.domain.bo.AuthRole;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.vo.Message;
import com.ai.service.ResourceService;
import com.ai.service.RoleService;
import com.ai.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 20:02 2018/3/20
 */
@RequestMapping("/web/api/v1/role")
@RestController
public class RoleController extends BasicAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ShiroFilterChainManager shiroFilterChainManager;

    @Autowired
    private AccountService accountService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色关联的(roleId)对应用户列表",httpMethod = "GET")
    @GetMapping("user/{roleId}/{currentPage}/{pageSize}")
    public Message getUserListByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<AuthUser> users = userService.getUserListByRoleId(roleId);
        users.forEach(user->user.setPassword(null));
        PageInfo pageInfo = new PageInfo(users);
        return new Message().ok(6666,"return users success").addData("data",pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色未关联的用户列表", httpMethod = "GET")
    @GetMapping("user/-/{roleId}/{currentPage}/{pageSize}")
    public Message getUserListExtendByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthUser> users = userService.getNotAuthorityUserListByRoleId(roleId);
        users.forEach(user -> user.setPassword(null));
        PageInfo pageInfo = new PageInfo(users);
        return new Message().ok(6666, "return users success").addData("data", pageInfo);
    }


    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色(roleId)所被授权的API资源")
    @GetMapping("api/{roleId}/{currentPage}/{pageSize}")
    public Message getRestApiExtendByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getAuthorityApisByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok(6666, "return api success").addData("data", pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色(roleId)未被授权的API资源")
    @GetMapping("api/-/{roleId}/{currentPage}/{pageSize}")
    public Message getRestApiByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getNotAuthorityApisByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok(6666, "return api success").addData("data", pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色(roleId)所被授权的menu资源")
    @GetMapping("menu/{roleId}/{currentPage}/{pageSize}")
    public Message getMenusByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getAuthorityMenusByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok(6666, "return api success").addData("data", pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色(roleId)未被授权的menu资源")
    @GetMapping("menu/-/{roleId}/{currentPage}/{pageSize}")
    public Message getMenusExtendByRoleId(@PathVariable Integer roleId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthResource> authResources = resourceService.getNotAuthorityMenusByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(authResources);
        return new Message().ok(6666, "return api success").addData("data", pageInfo);
    }

    @ApiOperation(value = "授权资源给角色",httpMethod = "POST")
    @PostMapping("/authority/resource")
    public Message authorityRoleResource(HttpServletRequest request) {
        Map<String,String> map = getRequestBody(request);
        int roleId = Integer.valueOf(String.valueOf(map.get("roleId")));
        int resourceId = Integer.valueOf(String.valueOf(map.get("resourceId")));
        boolean flag = roleService.authorityRoleResource(roleId,resourceId);
        shiroFilterChainManager.reloadFilterChain();
        return flag ? new Message().ok(6666,"authority success") : new Message().error(1111,"authority error");
    }

    @ApiOperation(value = "删除对应的角色的授权资源",httpMethod = "DELETE")
    @DeleteMapping("/authority/resource/{roleId}/{resourceId}")
    public Message deleteAuthorityRoleResource(@PathVariable Integer roleId, @PathVariable Integer resourceId ) {
        boolean flag = roleService.deleteAuthorityRoleResource(roleId,resourceId);
        shiroFilterChainManager.reloadFilterChain();
        return flag ? new Message().ok(6666,"authority success") : new Message().error(1111,"authority error");
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取角色LIST", httpMethod = "GET")
    @GetMapping("{currentPage}/{pageSize}")
    public Message getRoles(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        List<AuthRole> roles = roleService.getRoleList();
        PageInfo pageInfo = new PageInfo(roles);
        return new Message().ok(6666, "return roles success").addData("data", pageInfo);
    }

    @ApiOperation(value = "添加角色", httpMethod = "POST")
    @PostMapping("")
    public Message addRole(@RequestBody AuthRole role) {

        boolean flag = roleService.addRole(role);
        if (flag) {
            return new Message().ok(6666, "add role success");
        } else {
            return new Message().error(111, "add role fail");
        }
    }

    @ApiOperation(value = "更新角色", httpMethod = "PUT")
    @PutMapping("")
    public Message updateRole(@RequestBody AuthRole role) {

        boolean flag = roleService.updateRole(role);
        if (flag) {
            return new Message().ok(6666, "update success");
        } else {
            return new Message().error(1111, "update fail");
        }
    }

    @ApiOperation(value = "根据角色ID删除角色", httpMethod = "DELETE")
    @DeleteMapping("{roleId}")
    public Message deleteRoleByRoleId(@PathVariable Integer roleId) {

        boolean flag = roleService.deleteRoleByRoleId(roleId);
        if (flag) {
            return new Message().ok(6666, "delete success");
        } else {
            return new Message().error(1111, "delete fail");
        }
    }


    @ApiOperation(value = "权限信息", notes = "获取当前登陆用户的所有权限信息")
    @PostMapping("/roles")
    public Message accountRoles(HttpServletRequest request) {
        String appId = request.getHeader("appId");
        if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回账号信息缺失
            return new Message().error(1111, "账户信息缺失");
        }
        String roleId = accountService.loadAccountRoleId(appId);
        List<AuthRole> roleList = new ArrayList<>();
        roleList = roleService.getRolesById(Integer.parseInt(roleId));

        class RoleKv{
            public int id;
            public String name;

            public RoleKv(int id, String name) {
                this.id = id;
                this.name = name;
            }
        }

        List<RoleKv> rkv = new ArrayList<>();
        for (AuthRole role:roleList) {
            rkv.add(new RoleKv(role.getId(),role.getName()));
        }

        if (roleList != null) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(appId, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "获取成功"));
            return new Message().ok(2002, "获取成功").addData("roleList",rkv);
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(appId, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "获取失败"));
            return new Message().ok(1111, "获取失败");
        }
    }
}
