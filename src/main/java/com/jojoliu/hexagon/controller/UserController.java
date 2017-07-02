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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by Jojo on 19/05/2017.
 */

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

//    @Autowired
//    private RedisTemplate<String, ?> redisTemplate;

//    @RequestMapping(value = "/api/user/signIn", method = RequestMethod.POST)
//    public Response signIn(String phoneNumber, String verificationCode) {
//        logger.info("UserController#signIn,phoneNumber:{},verificationCode:{}", phoneNumber, verificationCode);
//        //核心逻辑
//        String content = phoneNumber + "   " + verificationCode;
//        //返回正常结果
//        return Response.ok(content);
//    }


    //异常情形处理方式

    @RequestMapping(value = "/api/user/signIn", method = RequestMethod.POST)
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

    @RequestMapping(value = "/api/user/updateInfo", method = RequestMethod.POST)
    public Response updateInfo(User user) {
        logger.info("UserController#updateInfo,userid:{}", user.getUserid());
        //核心逻辑,调用service
        //实现非空性校验
        if (Objects.isNull(user) || Objects.isNull(user.getUserid())) {
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
    @ResponseBody
    public Response signOut(HttpSession session) {
        session.invalidate();
        String content = "signOut successfully";
        //返回正常结果
        return Response.ok(content);
    }



    //利用redis实现共享session
    /*
    通过Redis进行session共享，每个服务器都去redis中去取session信息，键值对可以是token-用户信息
    */

//    @RequestMapping(value = "/user/{token}",
//            method = RequestMethod.GET,
//            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
//    public void getUser(@PathVariable final String token, HttpServletRequest request, HttpServletResponse response) {
//        String id = redisTemplate.execute(new RedisCallback<String>() {
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//                byte[] value = connection.get(serializer.serialize(token));
//                return serializer.deserialize(value);
//            }
//        });
//        writeJson(port + ":" + id, response);
//    }

//    @RequestMapping("/rest/user")
//    public void getUser(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        String loginname = (String) session.getAttribute("loginname");
//        writeJson(loginname + " : " + session.getId(), response);
//    }
//
//    public void writeJson(Object object, HttpServletResponse response) {
//        try {
//            //DisableCircularReferenceDetect避免$ref问题
//            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write(json);
//            System.out.println("This is the server:" + port);
//            response.getWriter().flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


