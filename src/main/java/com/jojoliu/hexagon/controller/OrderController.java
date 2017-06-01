package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.Order;
import com.jojoliu.hexagon.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jojo on 23/05/2017.
 */

@RestController
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

//    @RequestMapping(value = "/rest/order/{orderid}/{price}/{postid}/{consultantid}",
//            method = RequestMethod.POST,
//            produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
//    public void saveOrder(@PathVariable Long orderid,
//                          @PathVariable Long price,
//                          @PathVariable Long postid,
//                          @PathVariable Long consultantid,HttpServletRequest request,HttpServletResponse response){
//        Order order=new Order();
//        order.setOrderid(orderid);
//        order.setPrice(price);
//        order.setPostid(postid);
//        order.setConsultantid(consultantid);
//        try {
//            orderService.sendOrderMessage(Long.toString(orderid),order);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("返回错误信息到客户端");
//        }
//    }


    @RequestMapping(value = "/order/list/{consultantid}", method = RequestMethod.GET)
    private Response listByConsultantid(@PathVariable Long consultantid) {
        logger.info("订单列表查询开始：");
        Response response = new Response();
        List<Order> orderList = orderService.listByConsultantid(consultantid);
        response.setResult(orderList);
        logger.info("订单列表查询结束：{}", response);
        return response;
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    @ApiOperation(value="创建订单接口", notes="该接口用于创建业务订单")
    private Response add(@RequestBody Order order) {
        logger.info("添加订单开始：{}", order);
        Response response = new Response();
        order = orderService.add(order);
        response.setResult(order);
        logger.info("添加订单结束：{}", response);
        return response;
    }

    @RequestMapping(value = "/order/delete/{orderid}", method = RequestMethod.DELETE)
    private Response delete(@PathVariable Long orderid) {
        logger.info("添加订单开始：{}", orderid);
        Response response = new Response();
        orderService.delete(orderid);
        logger.info("添加订单结束：{}", response);
        return response;
    }

    @RequestMapping(value = "/order/addAndPay", method = RequestMethod.POST)
    private Response addAndPay(@RequestBody Order order) {
        logger.info("下单并支付开始：{}", order);
        Response response = orderService.addAndPay(order);
        response.setResult(order);
        logger.info("下单并支付结束：{}", response);
        return response;
    }
}
