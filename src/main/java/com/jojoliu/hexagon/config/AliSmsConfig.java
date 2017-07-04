package com.jojoliu.hexagon.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jojoliu.hexagon.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jojo on 03/07/2017.
 */
public class AliSmsConfig {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private AliSmsConfig() {}
    static final String signName = "商业咨询";//签名
    static final String templateCode = "SMS_75925043";//模版
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAItrHUHBvcIHp4";//此处私钥 填写自己的
    static final String accessKeySecret = "4AT1rwp14ZRJ2SgLFctyUac2GBkbDi";//此处私钥 填写自己的
    static final IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
    static {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout","10000");
            System.setProperty("sun.net.client.defaultReadTimeout","10000");
            DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou", product, domain);
        } catch(ClientException e) {
            logger.warn(e.getMessage(), e);
//            e.printStackTrace();
        }
    }
    /**
    * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
    * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
    */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static IAcsClient acsClient = new DefaultAcsClient(profile);
    }

    public static IAcsClient getAcsClient(){
        return SingletonHolder.acsClient;
    }
}
