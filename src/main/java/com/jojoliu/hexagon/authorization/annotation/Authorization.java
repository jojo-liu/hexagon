package com.jojoliu.hexagon.authorization.annotation;

/**
 * Created by Jojo on 04/07/2017.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，未登录返回401错误
 * @see com.jojoliu.hexagon.authorization.interceptor.AuthorizationInterceptor
 * @author ScienJus
 * @date 2015/7/31.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}