package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}