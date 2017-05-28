package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> selectByConsultantid(Long consultantid);

    List<Order> selectByPostid(Long postid);
}