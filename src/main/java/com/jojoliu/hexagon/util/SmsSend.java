package com.jojoliu.hexagon.util;

import com.aliyun.mns.common.ClientException;
import com.jojoliu.hexagon.controller.UserController;
import org.slf4j.Logger;

/**
 * Created by Jojo on 03/07/2017.
 */
public class SmsSend {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
    public static void main(String[] args) throws ClientException, InterruptedException{
//        SendSmsRequest request = new SendSmsRequest();
//        //必填:待发送手机号
//        request.setPhoneNumbers("15893967082");
//        //尊敬的${name}，您正进行身份验证，验证码${number}，打死不告诉别人！
//        JsonObject params = new JsonObject();
//        params.addProperty("name","兆龙");
//        params.addProperty("number","111111");
//        request.setTemplateParam(params.toString());
//        SendSmsResponse response = SmsUtil.sendSms(request);
//        logger.info("--------短信接口返回的数据--------");
//        if("OK".equals(response.getCode())){
//            System.out.println("Code=" + response.getCode());
//            System.out.println("Message=" + response.getMessage());
//            System.out.println("RequestId=" + response.getRequestId());
//            System.out.println("BizId=" + response.getBizId());
//            logger.info("短信发送成功");
//        }
    }
}
