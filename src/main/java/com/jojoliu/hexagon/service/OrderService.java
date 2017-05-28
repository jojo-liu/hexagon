package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.Order;

import java.util.List;

/**
 * Created by Jojo on 23/05/2017.
 */

public interface OrderService {
//    void confirm(CorrelationData correlationData, boolean ack, String cause);
//    void saveOrder(String id);
    void sendOrderMessage(String correlationId,Object order) throws Exception;

    List<Order> listByConsultantid(Long consultantid);
    List<Order> listByPostid(Long postid);

    //下单
    Order add(Order order);

    //下单并支付
    Response addAndPay(Order order);

    int delete(Long orderid);
}
