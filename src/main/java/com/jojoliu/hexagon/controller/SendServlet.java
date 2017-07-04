package com.jojoliu.hexagon.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.google.gson.JsonObject;
import com.jojoliu.hexagon.config.SmsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jojo on 03/07/2017.
 */
public class SendServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobile = request.getParameter("mobile");//手机
        String number = request.getParameter("number");//验证码
        SendSmsRequest sms = new SendSmsRequest();
        sms.setPhoneNumbers(mobile);
        JsonObject params = new JsonObject();
        params.addProperty("name","小柒");
        params.addProperty("number",number);
        sms.setTemplateParam(params.toString());
        SendSmsResponse res = SmsUtil.sendSms(sms);
        PrintWriter out = response.getWriter();
        if("OK".equals(res.getCode())){
            out.print("success");
        }else{
            out.print("fail");
        }
    }
}
