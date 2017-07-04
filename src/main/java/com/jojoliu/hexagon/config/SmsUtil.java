package com.jojoliu.hexagon.config;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.jojoliu.hexagon.controller.UserController;
import org.slf4j.Logger;

/**
 * Created by Jojo on 03/07/2017.
 */
public class SmsUtil {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

    public static SendSmsResponse sendSms(SendSmsRequest request){
        SendSmsResponse sendSmsResponse = null;
        logger.info("发送手机验证码:"+ request.getPhoneNumbers());
        try {
            IAcsClient acsClient = AliSmsConfig.getAcsClient();
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(AliSmsConfig.signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(AliSmsConfig.templateCode);
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch(Exception e) {
            logger.error("短信发送异常:" + request.getPhoneNumbers(), e);
        }
        return sendSmsResponse;
    }
}
