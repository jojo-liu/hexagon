package com.jojoliu.hexagon.service;


import com.google.common.base.Objects;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * Created by Jojo on 20/05/2017.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response<User> login(String username, String password) {
        Response<User> response = new Response<User>();
        try{
            checkArgument(!StringUtils.isEmpty(username), "用户名为空");
            User user = userMapper.selectByUsername(username);
            checkArgument(user != null, "用户名不存在");
            String realPassword = user.getPassword();
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            checkArgument(Objects.equal(md5Password, realPassword), "密码错误");
//            if(back){
//                checkArgument(user.getAdmin()==1, "没有权限");
//            }
//            user.setLastLoginIp(ip);
//            user.setLastLoginTime(new Date());
            response.setResult(user);
        } catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public Response<User> register(User user) {
        Response<User> response = new Response<User>();
        try{
            checkArgument(!(null == user), "用户名为空");
            String username = user.getUsername();
            checkArgument(!StringUtils.isEmpty(username), "用户名为空");
            String password = user.getPassword();
            checkArgument(!StringUtils.isEmpty(password), "密码为空");
            User userResult = userMapper.selectByPrimaryKey(user.getUserid());
            checkArgument(userResult == null, "用户名已存在");
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            user.setPassword(md5Password);
            userMapper.insert(user);
            response.setResult(user);
        } catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
