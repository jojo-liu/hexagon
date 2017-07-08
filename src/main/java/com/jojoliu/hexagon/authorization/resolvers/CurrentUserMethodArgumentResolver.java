package com.jojoliu.hexagon.authorization.resolvers;

import com.jojoliu.hexagon.authorization.annotation.CurrentUser;
import com.jojoliu.hexagon.config.Constants;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Objects;

/**
 * Created by Jojo on 05/07/2017.
 */

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        String currentUserId = (String) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
            //从数据库中查询并返回
            System.out.printf("currentUserId is not null");
            Object currentUser = userMapper.selectByPrimaryKey(currentUserId);
            if(!Objects.isNull(currentUser)) {
                return currentUser;
            }
            //有key但是得不到用户，抛出异常
            throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
        }
        else {
            System.out.printf("currentUserId is null and there is no record in database");
            System.out.printf(currentUserId);
        }
        //没有key就直接返回null
        return null;
    }
}
