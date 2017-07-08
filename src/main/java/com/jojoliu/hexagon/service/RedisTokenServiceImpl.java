package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.config.Constants;
import com.jojoliu.hexagon.model.TokenModel;
import com.jojoliu.hexagon.util.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jojo on 04/07/2017.
 */

@Component
public class RedisTokenServiceImpl implements TokenService {

    private RedisTemplate<String, String> redis;

    @Autowired
    @Qualifier("redisTemplate")
    public void setRedis (RedisTemplate redis) {
        this.redis = redis;
        // 泛型设置成 Long 后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }
    @Override
    public TokenModel createToken(String userId) {
        // 使用 uuid 作为源 token
        String token = UUidUtil.generate();
        TokenModel model = new TokenModel(userId, token);
        // 存储到 redis 并设置过期时间
//        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_SECOND, TimeUnit.SECONDS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = (String)redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals (model.getToken())) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
//        redis.boundValueOps (model.getUserId()).expire (Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        redis.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_SECOND, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length () == 0) {
            return null;
        }
        String[] param = authentication.split ("_");
        if (param.length != 2) {
            return null;
        }
        // 使用 userId 和源 token 简单拼接成的 token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(String userId) {
        if(userId != null && userId.length() >= 0) {
            redis.delete(userId);
        } else {
            System.out.printf("userId is null");
        }
    }
}
