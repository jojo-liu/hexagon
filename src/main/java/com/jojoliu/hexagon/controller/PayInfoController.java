package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.PayInfo;
import com.jojoliu.hexagon.service.PayInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jojo on 26/05/2017.
 */

@RestController
@RequestMapping("/pay")
public class PayInfoController {
    private static final Logger logger = LoggerFactory.getLogger(PayInfoController.class);
    @Autowired
    private PayInfoService payInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private Response list() {
        logger.info("支付订单列表查询开始：");
        Response response = new Response();
        List<PayInfo> payInfoList = payInfoService.list();
//        response.setResult(payInfoList);
        logger.info("支付订单列表查询结束：{}", response);
        return response;
    }

//    @RequestMapping(value = "/pay", method = RequestMethod.POST)
//    private Response pay(@RequestBody PayInfo payInfo) {
//        logger.info("支付开始：{}", payInfo);
////        Response response = payInfoService.pay(payInfo);
//        logger.info("支付结束：{}", response);
//        return response;
//    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private Response delete(@PathVariable Long id) {
        logger.info("删除支付订单开始：{}", id);
        Response response = new Response();
        payInfoService.delete(id);
        logger.info("删除支付订单结束：{}", response);
        return response;
    }

//    @RequestMapping("/find")
//    public Response find(@RequestBody PayInfo payInfo) {
//        logger.info("查询订单开始:{}", payInfo);
//        List<PayInfo> payInfoList = payInfoService.find(payInfo);
//        Response response = new Response();
//        response.setResult(payInfoList);
//        logger.info("查询订单结束:" + response);
//        return response;
//    }
}
