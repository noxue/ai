package com.ai.controller.web.v1;

import com.ai.domain.bo.AuthUserInfo;
import com.ai.domain.bo.Sip;
import com.ai.domain.bo.SipUser;
import com.ai.domain.vo.Message;
import com.ai.service.AuthUserInfoService;
import com.ai.service.SipService;
import com.ai.service.SipUserService;
import com.ai.util.RequestResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/* *
 * @Author ws
 * @Description
 * @2018-9-12 9:59:47
 */
@RestController
@RequestMapping("/web/api/v1/sip")
public class SipController extends BasicAction {

    @Autowired
    private SipService sipService;

    @Autowired
    private SipUserService sipUserService;

    @Autowired
    private AuthUserInfoService authUserInfoService;

    @ApiOperation(value = "新增sip", notes = "增加一个新的,name不可重复的sip")
    @ResponseBody
    @PostMapping("/add")
    public Message sipRegister(HttpServletRequest request, HttpServletResponse response) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String name = params.get("name");
        String username = params.get("username");
        String password = params.get("password");
        String host = params.get("host");

        if (StringUtils.isEmpty(name) ||StringUtils.isEmpty(username) ||StringUtils.isEmpty(password) ||
            StringUtils.isEmpty(host) ) {
            // 必须信息缺一不可,返回请检查 线路名，用户名，密码，端口
            return new Message().error(3001, "线路名");
        }
        if (sipService.isSipExistByName(name)) {
            // name已存在
            return new Message().error(3002, "名称已被占用");
        }
        String firms = params.get("firms");
        String maxThread = params.get("maxThread");
        String remark = params.get("remark");

        Sip sip = new Sip();
        sip.setName(name);
        sip.setUsername(username);
        sip.setPassword(password);
        sip.setHost(host);
        sip.setCharged(BigDecimal.valueOf(0.00));
        sip.setCreateat(new Date());
        sip.setFirms(firms);
        sip.setMaxthread(Integer.parseInt(maxThread));
        sip.setCurrentthread(0);
        sip.setRemark(remark);

        if (sipService.registerSip(sip)) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3004, "新增失败");
        }
    }

    @ApiOperation(value = "编辑sip", notes = "修改sip信息，修改后的name不可重复")
    @ResponseBody
    @PostMapping("/edit")
    public Message editSip(HttpServletRequest request, HttpServletResponse response) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);

        String id = params.get("id");
        String name = params.get("name");
        String username = params.get("username");
        String password = params.get("password");
        String host = params.get("host");

        if (StringUtils.isEmpty(name) ||StringUtils.isEmpty(username) ||StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(host) ){
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3005, "sip信息缺失");
        }
        if (sipService.isSipExistByName(name, id)) {
            return new Message().error(3002, "名称已被占用");
        }

        String firms = params.get("firms");
        String maxThread = params.get("maxThread");
        String currentThread = params.get("currentThread");
        String remark = params.get("remark");

        Sip sip = new Sip();
        sip.setId(Long.parseLong(id.trim()));
        sip.setName(name);
        sip.setUsername(username);
        sip.setPassword(password);
        sip.setHost(host);
        if(!StringUtils.isEmpty(firms)){
            sip.setFirms(firms);
        }
        if(!StringUtils.isEmpty(maxThread)){
            sip.setMaxthread(Integer.parseInt(maxThread));
        }
        if(!StringUtils.isEmpty(currentThread)){
            sip.setCurrentthread(Integer.parseInt(currentThread));
        }
        if(!StringUtils.isEmpty(remark)){
            sip.setRemark(remark);
        }

        if (sipService.editSip(sip)) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3007, "编辑失败");
        }
    }

    @ApiOperation(value = "删除sip", notes = "删除sip信息")
    @ResponseBody
    @GetMapping("/del/{id}")
    public Message delSip(HttpServletRequest request, @PathVariable("id") String id) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3012, "sip信息缺失");
        }

        if (sipService.delSip(Long.parseLong(id))) {
            return new Message().ok(0, "success");
        } else {
            return new Message().error(3009, "删除失败");
        }
    }

    /* *
     * @Description 分页获取所有sip
     * @Param [] name
     * @Return com.ai.domain.bo.sip.java
     */
    @ApiOperation(value = "分页获取sip", notes = "模糊查询分页获取sip信息，用于页面展示")
    @ResponseBody
    @PostMapping("/all")
    public Object findAllSip(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                     int pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                                     int pageSize) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String name = params.get("name");
        pageNum = Integer.parseInt(params.get("page"));
        PageInfo<Sip> sipList = sipService.findAllSip(pageNum, pageSize, name);
        if ( sipList!= null) {
            return new Message().ok(0, "success").addData("sipList", sipList);
        } else {
            return new Message().error(3014, "查询失败");
        }
    }

    /* *
     * @Description 获取所有sip
     * @Param []
     * @Return com.ai.domain.bo.sip.java
     */
    @ApiOperation(value = "获取所有sip信息", notes = "用于下拉框选择")
    @ResponseBody
    @PostMapping("/allSip")
    public Object findAll(HttpServletRequest request, HttpServletResponse response) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }

        PageInfo<Sip> sips= sipService.findAllSip(1, 111111111, "");
        if (sips != null) {
            List<Sip> sipList = sips.getList();
            return new Message().ok(0, "success").addData("sipList",sipList);
        } else {
            return new Message().error(3014, "查询失败");
        }
    }

    /* *
     * @Description 根据id获取sip信息
     * @Param [] id
     * @Return com.ai.domain.bo.sip.java
     */
    @ApiOperation(value = "获取sip", notes = "根据id获取sip信息")
    @ResponseBody
    @GetMapping("/{id}")
    public Object selectSipById(HttpServletRequest request, @PathVariable("id") String id ) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        if (StringUtils.isEmpty(id)) {
            // 必须信息缺一不可,返回信息缺失
            return new Message().error(3012, "sip信息缺失");
        }
        Sip sip = sipService.getSipById(Long.parseLong(id));
        if (sip != null) {
            return new Message().ok(0, "success").addData("sip", sip);
        } else {
            return new Message().error(3011, "查询失败");
        }
    }

    @ApiOperation(value = "新增sipUser", notes = "新增sipUser")
    @ResponseBody
    @PostMapping("/user/add")
    public Message sipUserRegister(HttpServletRequest request, HttpServletResponse response) {
        String appId = request.getHeader("appId");
            if (StringUtils.isEmpty(appId)) {
            // 必须信息缺一不可,返回请检查用户，线路
            return new Message().error(3001, "请登陆后操作");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);

        String user_id = params.get("userid");
        if (StringUtils.isEmpty(user_id)) {
            // 必须信息缺一不可,返回请检查用户，线路
            return new Message().error(3001, "请确认信息后重新操作");
        }
        if(user_id.equals(appId)){
            return new Message().error(3001, "请重新选择用户");
        }
        String toAss = params.get("toAss");
        String forAss = params.get("forAss");

        if(!appId.equals("admin")){
            AuthUserInfo aui = authUserInfoService.getUserById(appId);
            AuthUserInfo aui1 = authUserInfoService.getUserById(user_id);
            if((aui.getThreadNum() + aui1.getThreadNum())!=(Integer.parseInt(toAss) +Integer.parseInt(forAss) )){
                return new Message().error(3004, "操作失败,请刷新后重试");
            }
            authUserInfoService.editUser(appId,Integer.parseInt(toAss));
        }
        authUserInfoService.editUser(user_id,Integer.parseInt(forAss));


        String sip_id = params.get("sip_id");
        //数据库中的sip_id集合
        List<SipUser> lists = sipUserService.findSipUser(user_id);
        //转换成数组
        String [] org_id = new String[lists.size()];
        for (int  i = 0; i<lists.size();i++ ){
            org_id[i] = lists.get(i).getSipId().toString();
        }
        //将前台传来sip转换成数组
        String[] sids=sip_id.split(",");

        List<String> compareTag = new ArrayList<String>();
        List<String> compareOrg= new ArrayList<String>();
        //用数据库的sip数组 与 前台sip数组 比较  不同的删除
        for (String num:org_id) {
            if (Arrays.binarySearch(sids, num)<0){
                compareTag.add(num);
            }
        }
        //用前台sip数组  与 数据库的sip数组 比较 不同的增加
        for (String num: sids) {
            if (Arrays.binarySearch(org_id, num)<0){
                compareOrg.add(num);
            }
        }
        if(compareTag.size()>0 && !StringUtils.isEmpty(compareTag.get(0))){
            List<String> delId = new ArrayList<>();
            for (int i = 0; i<lists.size();i++){
                for(int j=0;j<compareTag.size();j++){
                    if(compareTag.get(j).equals(lists.get(i).getSipId().toString())){
                        delId.add(lists.get(i).getId().toString());
                    }
                }
            }
            sipUserService.delSipUserList(delId);
        }
        if(compareOrg.size()>0 && !StringUtils.isEmpty(compareOrg.get(0))){
            List<SipUser> list =  new ArrayList<>();

            for (int i=0 ; i<compareOrg.size(); i++){
                if(!sipUserService.checkRepeat(user_id,compareOrg.get(i))){
                    SipUser sipUser = new SipUser();
                    sipUser.setSipId(Long.parseLong(compareOrg.get(i)));
                    sipUser.setUserId(user_id);
                    list.add(sipUser);
                }
            }
            if(list.size()==0){
                return new Message().ok(0, "当前线路已分配完成，请勿重复操作");
            }else {
                boolean flag = sipUserService.insertSipUserList(list);
                if (flag) {
                    return new Message().ok(0, "success");
                } else {
                    return new Message().error(3004, "操作失败");
                }
            }

        }
        return new Message().ok(0, "success");
    }

    @ApiOperation(value = "获取sipUser集合", notes = "管理员使用另一个方法")
    @ResponseBody
    @GetMapping("/user/all")
    public Message selectSipUserList(HttpServletRequest request, HttpServletResponse response) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        String appId = request.getHeader("appId");
        if (StringUtils.isEmpty(appId) ) {
            return new Message().error(3001, "请确认信息后重新操作");
        }
        List<Sip> sipList =  sipUserService.findAllSipUser(appId);
        sipList.forEach(sip->sip.setUsername(null));
        sipList.forEach(sip->sip.setPassword(null));
        sipList.forEach(sip->sip.setHost(null));
        return new Message().ok(0, "success").addData("sipList",sipList);
    }

    @ApiOperation(value = "根据用户获取sip", notes = "用于穿梭框右边展示")
    @ResponseBody
    @PostMapping("/user/list")
    public Message selectSipUsersList(HttpServletRequest request, HttpServletResponse response) {
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String user_id = params.get("userid");
        if (StringUtils.isEmpty(user_id) ) {
            return new Message().error(3001, "请确认信息后重新操作");
        }
        if(uid.equals(user_id)){
            return new Message().error(3001, "请确认信息后重新操作");
        }
        List<Sip> sipList =  sipUserService.findAllSipUser(user_id);
        sipList.forEach(sip->sip.setUsername(null));
        sipList.forEach(sip->sip.setPassword(null));
        sipList.forEach(sip->sip.setHost(null));

        List<Object> userInfos = new ArrayList<>();
        AuthUserInfo aui = new AuthUserInfo();
        if(uid.equals("admin")){
            userInfos.add(1000);
        }else{
            aui = authUserInfoService.getUserById(uid);
            if(aui == null){
                userInfos.add(0);
                AuthUserInfo aui1 = new AuthUserInfo();
                aui1.setUserId(uid);
                aui1.setThreadNum(0);
                aui1.setCharged(new BigDecimal(0.00));
                authUserInfoService.addUser(aui1);
                if( !authUserInfoService.addUser(aui1)){
                    return new Message().error(3001, "请确认信息后重新操作");
                }
            }else{
                userInfos.add( authUserInfoService.getUserById(uid).getThreadNum());
            }
        }
        aui = authUserInfoService.getUserById(user_id);
        if(aui == null){
            userInfos.add(0);
            AuthUserInfo aui2 = new AuthUserInfo();
            aui2.setUserId(user_id);
            aui2.setThreadNum(0);
            aui2.setCharged(new BigDecimal(0.00));
            authUserInfoService.addUser(aui2);
            if(!authUserInfoService.addUser(aui2)){
                return new Message().error(3001, "请确认信息后重新操作");
            }
        }else{
            userInfos.add( authUserInfoService.getUserById(user_id).getThreadNum());
        }
        return new Message().ok(0, "success").addData("userList",sipList).addData("userInfos",userInfos);
    }

    @ApiOperation(value = "获取sip信息,线路列表", notes = "根据当前用户查询sip信息")
    @ResponseBody
    @PostMapping("/user/listById")
    public Object findAllSipById(HttpServletRequest request, HttpServletResponse response){
        String uid =request.getHeader("appId");
        if (StringUtils.isEmpty(uid)) {
            // 必须信息缺一不可,返回网关信息缺失
            return new Message().error(4004, "当前用户未登录");
        }
        PageInfo<Sip> sipList = null;
        List<Sip> userList = null;
        if(uid.equals("admin")){
            sipList = sipService.findAllSip(1,1000,"");
            userList = sipList.getList();
        }else{
            userList= sipUserService.findAllSipUser(uid);
        }
        userList.forEach(sip->sip.setUsername(null));
        userList.forEach(sip->sip.setPassword(null));
        userList.forEach(sip->sip.setHost(null));
        return new Message().ok(0, "success").addData("sipList",userList);
    }
}
