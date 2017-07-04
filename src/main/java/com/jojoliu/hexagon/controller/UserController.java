package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by Jojo on 19/05/2017.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public Response signIn(String phoneNumber, String verificationCode) {
        logger.info("UserController#signIn,phoneNumber:{},verificationCode:{}", phoneNumber, verificationCode);
        if (Objects.isNull(phoneNumber) || Objects.isNull(verificationCode)) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        //核心逻辑,调用service
        try {
            User user = userService.signIn(phoneNumber, verificationCode);
            return Response.ok(user);
            //xxService处理,该Service可能会抛出CommonException
//            throw new CommonException(ErrorCode.SERVER_ERROR);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public Response updateInfo(User user) {
        //核心逻辑,调用service
        //实现非空性校验
        if (Objects.isNull(user) || Objects.isNull(user.getUserId())) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            int content = userService.updateInfo(user);
            //userService处理,该Service可能会抛出CommonException
            return Response.ok(content);
//            throw new CommonException(ErrorCode.SERVER_ERROR);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/signOut")
    public Response signOut(HttpSession session) {
        session.invalidate();
        String content = "signOut successfully";
        //返回正常结果
        return Response.ok(content);
    }
}


