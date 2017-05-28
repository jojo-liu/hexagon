package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.JsonResponseException;
import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.Consultant;
import com.jojoliu.hexagon.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Jojo on 27/05/2017.
 */

@RestController
@RequestMapping("/consultant")
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    @RequestMapping(value = "/{tagid}")
    public String showConsultant(Model model, Page<Consultant> page, @PathVariable Long tagid, HttpSession session) {
        Response<Page<Consultant>> response = consultantService.showConsultant(page, tagid);
        if(!response.isSuccess()){
            throw new JsonResponseException(response.getError());
        }
        //通过model向页面传值
        model.addAttribute("page",response.getResult());
        return "consultant/page";
    }
}
