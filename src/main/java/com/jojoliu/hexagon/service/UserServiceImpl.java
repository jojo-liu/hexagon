package com.jojoliu.hexagon.service;


import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * Created by Jojo on 20/05/2017.
 */

@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public User signIn(String phoneNumber, String verificationCode) throws Exception {
        //从数据库中获取用户信息
        User user = userMapper.selectByUsername(phoneNumber);
//        checkArgument(user != null, "用户名不存在");
//        String realPassword = user.getPassword();
//        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
//        checkArgument(Objects.equal(md5Password, realPassword), "密码错误");
        return user;
    }

    @Override
    public int updateInfo(User user) throws Exception {
        //实现有效性校验
        User resultUser = userMapper.selectByPrimaryKey(user.getUserid());
        if(Objects.isNull(user)) {
            throw new CommonException(ErrorCode.USERINFO_ERROR);
        }
        int result = userMapper.updateByPrimaryKey(user);
        return result;
    }
}
