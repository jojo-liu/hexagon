package com.jojoliu.hexagon.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jojoliu.hexagon.annotation.MessageCache;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.config.AmqpConfig;
import com.jojoliu.hexagon.enums.OrderStatus;
import com.jojoliu.hexagon.mapper.OrderMapper;
import com.jojoliu.hexagon.model.Order;
import com.jojoliu.hexagon.model.PayInfo;
import com.jojoliu.hexagon.util.CacheCorrelationData;
import com.jojoliu.hexagon.util.NoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jojo on 23/05/2017.
 */

@Service
public class OrderServiceImpl implements OrderService {

    private String orderSaveKey= AmqpConfig.ROUNTING_KEY_PREFIX+"."+AmqpConfig.ORDER_SAVE_ROUTING_KEY;

    Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Value("${spring.rabbitmq.keys.orderBounding}")
//    private String boundingKey;

    private Gson gson = new Gson();

    @Value("${spring.rabbitmq.exchange}")
    private String orderExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PayInfoService payInfoService;
//    @Autowired
//    private RabbitmqProps rabbitmqProps;

    //@MessageCache为自定义注释，用来设置缓存和原交换机、路由键等相关信息
    @MessageCache(cacheName="order",cacheKey="${arg.correlationId}",messageArgMapper="order",exchange="${field.testExchange}",routingKey="${field.orderSaveKey}")


//    @Autowired
//    public OrderServiceImpl(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        rabbitTemplate.setConfirmCallback(this);
//    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println(" 回调id:" + correlationData);
//        if (ack) {
//            System.out.println("订单发送成功！");
//        } else {
//            System.out.println("订单发送失败，进行重新发送" + cause);
//        }
//    }

    //string id refers to the order id
    @Override
    public void sendOrderMessage(String correlationId,Object order) throws Exception{
        //扩展CorrelationData，使其包含缓存信息，方便确认机制返回失败后重发
        CacheCorrelationData correlation = new CacheCorrelationData(correlationId,"order");
        Message msg=new Message(JSONObject.toJSONString(order).getBytes(), MessagePropertiesBuilder.newInstance().setContentType("text/x-json").build());//.setExpiration("10000").build());
        rabbitTemplate.send(orderExchange, orderSaveKey, msg, correlation);
    }

    @Override
    public List<Order> listByConsultantid(Long consultantid) {
        return orderMapper.selectByConsultantid(consultantid);
    }

    @Override
    public List<Order> listByPostid(Long postid) {
        return orderMapper.selectByPostid(postid);
    }

    @Override
    public Order add(Order order) {
        logger.info("下单开始：{}", order);
        order.setCreatetime(new Date());
        order.setOrderno(NoUtil.generate("0"));
        order.setStatus(OrderStatus.I.name());
        orderMapper.insert(order);
        logger.info("下单结束：{}", order);
        return order;
    }

    @Override
    public Response addAndPay(Order order) {
        order = this.add(order);

        PayInfo payInfo = new PayInfo();
        payInfo.setOrderNo(order.getOrderno());
        payInfo.setPayChannel(order.getPaychannel());
        Response response = payInfoService.pay(payInfo);
        payInfo = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<PayInfo>() {}.getType());
        if (response.isSuccess()) {
            order.setStatus(OrderStatus.S.name());
        } else {
            if (payInfo != null && OrderStatus.F.name().equals(payInfo.getStatus())) {
                order.setStatus(OrderStatus.F.name());
            } else {
                order.setStatus(OrderStatus.P.name());
            }
        }
        response.setResult(order);
        orderMapper.updateByPrimaryKeySelective(order);
        return response;
    }

    @Override
    public int delete(Long orderid) {
        return orderMapper.deleteByPrimaryKey(orderid);
    }
}
