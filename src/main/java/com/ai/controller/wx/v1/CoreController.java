package com.ai.controller.wx.v1;

import com.ai.controller.web.v1.BasicAction;
import com.ai.service.wx.CoreService;
import com.ai.util.wx.SignUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/* *
 * @Author ws
 * @Description add新增,get读取,put完整更新,del删除
 * @2018年6月8日8:49:43
 */
@RestController
@RequestMapping("/wx/api/v1/core")
public class CoreController extends BasicAction{

    @ApiOperation(value = "接受请求", notes = "处理微信、用户发送的消息")
    @ResponseBody
    @RequestMapping(value="/doGet",method = {RequestMethod.GET,RequestMethod.POST})
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if("GET".equals(request.getMethod())){
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce     = request.getParameter("nonce");
            String echostr   = request.getParameter("echostr");

            PrintWriter out = response.getWriter();
            //如果校验成功
            if(SignUtil.checkSignature(signature, timestamp, nonce)){
                out.write(echostr);
            }
            out.close();
        }else{
            doPost(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //请求校验
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce     = request.getParameter("nonce");
        PrintWriter out = response.getWriter();
        //如果校验成功
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            //对消息进行处理
            String respXML = CoreService.processRequest(request);
            out.write(respXML);
        }
    }

}
