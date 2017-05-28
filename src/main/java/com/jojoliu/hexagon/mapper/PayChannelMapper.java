package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.PayChannel;

public interface PayChannelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayChannel record);

    int insertSelective(PayChannel record);

    PayChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayChannel record);

    int updateByPrimaryKey(PayChannel record);
}