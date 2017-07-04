package com.jojoliu.hexagon.service;

import org.springframework.stereotype.Service;

/**
 * Created by Jojo on 23/05/2017.
 */

@Service
public class OrderServiceImpl implements OrderService {

//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private Gson gson = new Gson();
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Autowired
//    private PayInfoServiceImpl payInfoServiceImpl;
//
//    @Override
//    public List<Order> listByConsultantid(Long consultantid) {
//        return orderMapper.selectByConsultantid(consultantid);
//    }
//
//    @Override
//    public List<Order> listByPostid(Long postid) {
//        return orderMapper.selectByPostid(postid);
//    }
//
//    @Override
//    public Order add(Order order) {
//        logger.info("下单开始：{}", order);
//        order.setCreatetime(new Date());
//        order.setOrderno(NoUtil.generate("0"));
//        order.setStatus(OrderStatus.I.name());
//        orderMapper.insert(order);
//        logger.info("下单结束：{}", order);
//        return order;
//    }
//
//    @Override
//    public Response addAndPay(Order order) {
//        order = this.add(order);
//
//        PayInfo payInfo = new PayInfo();
//        payInfo.setOrderNo(order.getOrderno());
//        payInfo.setPayChannel(order.getPaychannel());
//        Response response = payInfoServiceImpl.pay(payInfo);
//        payInfo = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<PayInfo>() {}.getType());
//        if (response.isSuccess()) {
//            order.setStatus(OrderStatus.S.name());
//        } else {
//            if (payInfo != null && OrderStatus.F.name().equals(payInfo.getStatus())) {
//                order.setStatus(OrderStatus.F.name());
//            } else {
//                order.setStatus(OrderStatus.P.name());
//            }
//        }
//        response.setResult(order);
//        orderMapper.updateByPrimaryKeySelective(order);
//        return response;
//    }
//
//    @Override
//    public int delete(Long orderid) {
//        return orderMapper.deleteByPrimaryKey(orderid);
//    }
}
