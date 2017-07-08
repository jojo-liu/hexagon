package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.authorization.annotation.Authorization;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.model.TokenModel;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.service.TokenService;
import com.jojoliu.hexagon.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private TokenService tokenService;


    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    public Response sendSms(String phoneNumber) throws Exception {
        if(Objects.isNull(phoneNumber)) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            boolean content = userService.sendSms(phoneNumber);
            return Response.ok(content);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<Response> signIn(String phoneNumber, String verificationCode) {
        logger.info("UserController#signIn,phoneNumber:{},verificationCode:{}", phoneNumber, verificationCode);
        if (Objects.isNull(phoneNumber) || Objects.isNull(verificationCode)) {
            return new ResponseEntity<>(Response.error(ErrorCode.PARAM_ERROR), HttpStatus.NOT_FOUND);
        }
        //核心逻辑,调用service
        try {
            User user = userService.signIn(phoneNumber, verificationCode);
            if(Objects.isNull(user)) {
                return new ResponseEntity<>(Response.error(ErrorCode.SERVER_ERROR), HttpStatus.NOT_FOUND);
            }
            TokenModel tokenModel = tokenService.createToken (user.getUserId());
            return new ResponseEntity<>(Response.ok(tokenModel), HttpStatus.OK);
            //xxService处理,该Service可能会抛出CommonException
//            throw new CommonException(ErrorCode.SERVER_ERROR);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return new ResponseEntity<>(Response.error(e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(Response.error(ErrorCode.SERVER_ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @Authorization
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
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

    @RequestMapping (value = "signOut", method = RequestMethod.DELETE)
    @Authorization
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
//    public ResponseEntity<Response> signOut(@CurrentUser User user) {
    public ResponseEntity<Response> signOut(String userId) {
//        tokenService.deleteToken (user.getUserId());
        tokenService.deleteToken(userId);
        String content = "signOut successfully";
        //返回正常结果
        return new ResponseEntity<>(Response.ok(content), HttpStatus.OK);
    }
}


