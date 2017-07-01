package com.jojoliu.hexagon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Jojo on 19/05/2017.
 */

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${server.port}")
    private String port;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    //利用redis实现共享session
    /*
    通过Redis进行session共享，每个服务器都去redis中去取session信息，键值对可以是token-用户信息
    */

    @RequestMapping(value = "/user/{token}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    public void getUser(@PathVariable final String token, HttpServletRequest request, HttpServletResponse response) {
        String id = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(token));
                return serializer.deserialize(value);
            }
        });
        writeJson(port + ":" + id, response);
    }

    //    @RequestMapping(value = "/rest/user",
//            method = RequestMethod.GET,
//            produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
    @RequestMapping("/rest/user")
    public void getUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String loginname = (String) session.getAttribute("loginname");
        writeJson(loginname + " : " + session.getId(), response);
    }

    @RequestMapping(value = "/login/{loginname}/{password}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    public void getUser(@PathVariable final String loginname, @PathVariable final String password,
                        HttpServletRequest request, HttpServletResponse response) {
        if (loginname.equals("admin") && password.equals("123456")) {
            HttpSession session = request.getSession();
            session.setAttribute("loginname", loginname);
            writeJson("登录成功!", response);
            System.out.println("用户登录成功");
        }
        else {
            System.out.println("用户信息验证失败!");
            writeJson("无效的用户信息!", response);
        }
    }

    public void writeJson(Object object, HttpServletResponse response) {
        try {
            //DisableCircularReferenceDetect避免$ref问题
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            System.out.println("This is the server:" + port);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public Object register(HttpSession session, User user) {
//        String ip = getRemoteHost(request);
//        user.setIp(ip);
//        user.setLastLoginTime(new Date());
        Response<User> response = userService.register(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if (!response.isSuccess()) {
            map.put("flag", false);
            map.put("errMsg", response.getError());
        }
        session.setAttribute("visitor", response.getResult());
        map.put("flag", true);
        return map;
    }

//    @RequestMapping("/login")
//    @ResponseBody
//    public Object login(HttpSession session, String username ,String password){
////        String ip = getRemoteHost(request);
//        Response<User> response = userService.login(username, password);
//        Map<String,Object> map = new HashMap<String,Object>();
//        if(!response.isSuccess()){
//            map.put("flag",false);
//            map.put("errMsg",response.getError());
//        }
//        User user = response.getResult();
//        session.setAttribute("visitor",user);
//        map.put("flag",true);
//        return map;
//    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpSession session) {
        session.invalidate();
        return true;
    }


    @RequestMapping(value = "/api/user/signIn", method = RequestMethod.POST)
    public Response signIn(String phoneNumber, String verificationCode) {
        logger.info("UserController#signIn,phoneNumber:{},verificationCode:{}", phoneNumber, verificationCode);
        //核心逻辑
        String result = phoneNumber + "   " + verificationCode;
        //返回正常结果
        return Response.ok(result);
    }


    //异常情形处理方式

    @RequestMapping(value = "/api/user/signIn", method = RequestMethod.POST)
    public Response signInError(String phoneNumber, String verificationCode) {
        logger.info("UserController#signIn,phoneNumber:{},verificationCode:{}", phoneNumber, verificationCode);
        if (Objects.isNull(phoneNumber) || Objects.isNull(verificationCode)) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        //核心逻辑,调用service
        try {
            //xxService处理,该Service可能会抛出CommonException
            throw new CommonException(ErrorCode.SERVER_ERROR);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.warn(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }
}


