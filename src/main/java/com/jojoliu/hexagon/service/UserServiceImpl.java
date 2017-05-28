package com.jojoliu.hexagon.service;


import com.google.common.base.Objects;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * Created by Jojo on 20/05/2017.
 */

@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Response<User> login(String username, String password) {
        Response<User> response = new Response<User>();
        try{
            checkArgument(!StringUtils.isEmpty(username), "用户名为空");
            //从缓存中获取用户信息
            String key = "user_" + username;
            ValueOperations<String, User> operations = redisTemplate.opsForValue();
            // 缓存存在
            boolean hasKey = redisTemplate.hasKey(key);

            User user;
            if (hasKey) {
                user = operations.get(key);

                logger.info("CityServiceImpl.selectByUsername() : 从缓存中获取了用户 >> " + user.toString());
            }
            else {
                //从数据库中获取用户信息
                user = userMapper.selectByUsername(username);
                operations.set(key, user, 10, TimeUnit.SECONDS);
                logger.info("CityServiceImpl.selectByUsername() : 用户插入缓存 >> " + user.toString());
            }
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
            String key = "user_" + username;
            ValueOperations<String, User> operations = redisTemplate.opsForValue();
            // 缓存存在
            boolean hasKey = redisTemplate.hasKey(key);

            User userResult;
            if (hasKey) {
                userResult = operations.get(key);
                logger.info("UserServiceImpl.selectByPrimaryKey() : 从缓存中获取了用户 >> " + user.toString());
            }
            else {
                userResult = userMapper.selectByUsername(user.getUsername());
                operations.set(key, user, 10, TimeUnit.SECONDS);
                logger.info("UserServiceImpl.selectByUsername() : 用户插入缓存 >> " + user.toString());
            }
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
        String key = "user_" + user.getUsername();
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("UserServiceImpl.selectByPrimaryKey() : 从缓存中删除了用户 >> " + user.toString());
        }

        operations.set(key, user, 10, TimeUnit.SECONDS);
        logger.info("UserServiceImpl.selectByUsername() : 用户插入缓存 >> " + user.toString());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
