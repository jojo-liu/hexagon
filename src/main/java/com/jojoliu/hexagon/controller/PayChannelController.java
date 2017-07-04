package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.service.PayChannelServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jojo on 26/05/2017.
 */

@RestController
@RequestMapping("/pay/channel")
public class PayChannelController {
    Logger logger = LoggerFactory.getLogger(PayChannelController.class);

    @Autowired
    private PayChannelServiceImpl payChannelServiceImpl;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    private Response list() {
//        logger.info("支付渠道列表查询开始：");
//        Response response = new Response();
//        List<PayChannel> payChannelList = payChannelServiceImpl.list();
//        response.setResult(payChannelList);
//        logger.info("支付渠道列表查询结束：{}", response);
//        return response;
//    }
}
