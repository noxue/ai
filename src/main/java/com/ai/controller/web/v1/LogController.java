package com.ai.controller.web.v1;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ai.domain.bo.AuthAccountLog;
import com.ai.domain.bo.AuthOperationLog;
import com.ai.domain.vo.Message;
import com.ai.service.AccountLogService;
import com.ai.service.OperationLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 12:20 2018/4/22
 */
@RestController
@RequestMapping("/web/api/v1/log")
public class LogController extends BasicAction {

    @Autowired
    AccountLogService accountLogService;

    @Autowired
    OperationLogService operationLogService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取日志记录", httpMethod = "GET")
    @RequestMapping("/accountLog/{currentPage}/{pageSize}")
    public Message getAccountLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize ) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthAccountLog> accountLogs = accountLogService.getAccountLogList();
        PageInfo pageInfo = new PageInfo(accountLogs);
        return new Message().ok(6666, "return accountLogs success").addData("data",pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户操作api日志列表", httpMethod = "GET")
    @RequestMapping("/operationLog/{currentPage}/{pageSize}")
    public Message getOperationLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthOperationLog> authOperationLogs = operationLogService.getOperationList();
        PageInfo pageInfo = new PageInfo(authOperationLogs);
        return new Message().ok(6666, "return operationLogList success").addData("data", pageInfo);
    }
}
