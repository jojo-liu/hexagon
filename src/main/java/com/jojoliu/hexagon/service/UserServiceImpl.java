package com.jojoliu.hexagon.service;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.google.gson.JsonObject;
import com.jojoliu.hexagon.config.SmsUtil;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.mapper.SmsMapper;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.Sms;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.util.UUidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Jojo on 20/05/2017.
 */

@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmsMapper smsMapper;

    /**
     * 手机号验证
     *
     * @param  phoneNumber
     * @return 验证通过返回true
     */
    public static boolean isMobile(String phoneNumber) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 创建指定数量的随机字符串
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    @Override
    public boolean sendSms(String phoneNumber) throws Exception {
        if(!isMobile(phoneNumber)) {
            throw new CommonException((ErrorCode.PHONE_NUMBER_ERROR));
        }
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //您正进行身份验证，验证码${number}，打死不告诉别人！
        JsonObject params = new JsonObject();
//        params.addProperty("name","兆龙");
        String verificationCode = createRandom(true, 6);
        params.addProperty("number",verificationCode);
        request.setTemplateParam(params.toString());
        SendSmsResponse response = SmsUtil.sendSms(request);
        logger.info("--------短信接口返回的数据--------");
        if("OK".equals(response.getCode())){
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());
            logger.info("短信发送成功");
            //删除所有包含此手机号码的验证码，并生成新的手机号-验证码记录到sms表中
            int deleteRecord = smsMapper.deleteByPhoneNumber(phoneNumber);
            Sms sms = new Sms();
            sms.setSmsId(UUidUtil.generate());
            sms.setPhoneNumber(phoneNumber);
            sms.setVerificationCode(verificationCode);
            sms.setSendTime(new Date());
            smsMapper.insert(sms);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User signIn(String phoneNumber, String verificationCode) throws Exception {
        //从数据库中获取用户信息
        User user = userMapper.selectByPhoneNumber(phoneNumber);
        if(Objects.isNull(user)) {
            //用户不存在，直接为当前手机号申请一个账户并存放到数据库中
            logger.info("user does not exist, a new user will be created in database");
            User newUser = new User();
            String userId = UUID.randomUUID().toString().replace("-", "");
            newUser.setUserId(userId);
            newUser.setUserName(phoneNumber);
            newUser.setPhoneNumber(phoneNumber);
            String md5Password = DigestUtils.md5DigestAsHex((phoneNumber + verificationCode).getBytes("utf-8"));
            newUser.setPassword(md5Password);
            newUser.setCreateTime(new Date());
            userMapper.insert(newUser);
            return newUser;
        } else {
            logger.info("user exist in database");
            Sms sms = smsMapper.selectByPhoneNumber(phoneNumber);
            if(Objects.isNull(sms)) {
                throw new CommonException(ErrorCode.VERIFICATION_CODE_ERROR);
            }
            String verificationCodeInDB = sms.getVerificationCode();
//            boolean codeValid = (((new Date()).getTime() - sms.getSendTime().getTime()) / 1000) > 60 ? false : true;
//            if(!codeValid) {
//                //验正码过期
//                throw new CommonException(ErrorCode.VERIFICATION_CODE_INVALID_ERROR);
//            }
            if(Objects.equals(verificationCode, verificationCodeInDB)) {
                return user;//此处需要生成token并由发送给客户端
            } else {
                throw new CommonException(ErrorCode.VERIFICATION_CODE_ERROR);
            }
        }

//        checkArgument(user != null, "用户名不存在");
//        String realPassword = user.getPassword();
//        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
//        checkArgument(Objects.equal(md5Password, realPassword), "密码错误");
    }

    @Override
    public int updateInfo(User user) throws Exception {
        //实现有效性校验
        User resultUser = userMapper.selectByPrimaryKey(user.getUserId());
        if(Objects.isNull(resultUser)) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }
        int result = userMapper.updateByPrimaryKey(user);
        return result;
    }
}
