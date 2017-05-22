package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jojo on 19/05/2017.
 */

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public Object register(HttpSession session, User user){
//        String ip = getRemoteHost(request);
//        user.setIp(ip);
//        user.setLastLoginTime(new Date());
        Response<User> response = userService.register(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if(!response.isSuccess()){
            map.put("flag", false);
            map.put("errMsg", response.getError());
        }
        session.setAttribute("visitor", response.getResult());
        map.put("flag",true);
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpSession session, String username ,String password){
//        String ip = getRemoteHost(request);
        Response<User> response = userService.login(username, password);
        Map<String,Object> map = new HashMap<String,Object>();
        if(!response.isSuccess()){
            map.put("flag",false);
            map.put("errMsg",response.getError());
        }
        User user = response.getResult();
        session.setAttribute("visitor",user);
        map.put("flag",true);
        return map;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(HttpSession session){
        session.invalidate();
        return true;
    }

}
