package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangzhaolong on 2017/5/16.
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/index")
    public String home() {
        return "home";
    }

    @RequestMapping("/home")
    public String index() {
        testService.test();
        return "index";
    }
}
