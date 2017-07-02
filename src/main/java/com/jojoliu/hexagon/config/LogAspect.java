package com.jojoliu.hexagon.config;

/**
 * Created by huangzhaolong on 2017/7/2.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 记录请求日志和异常日志的切面
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut(value = "execution (* com.jojoliu.hexagon.controller..*.*(..))")
    public void pointcut() {

    }

    /**
     * 配置打印每个请求的信息
     *
     * @param point
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        StringBuilder builder = new StringBuilder();
        Object[] args = point.getArgs();
        for (Object arg : args) {
            builder.append(arg).append(",");
        }
        logger.info(className + "#" + methodName + ",args:{}", builder.toString());
    }
}
