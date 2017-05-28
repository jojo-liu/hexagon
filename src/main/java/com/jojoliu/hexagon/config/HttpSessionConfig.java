package com.jojoliu.hexagon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by Jojo on 23/05/2017.
 */

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*60, redisNamespace = "applicationHexagon")
public class HttpSessionConfig {

    //通过httpSessionStrategy接口指定session传递的方式
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

}
