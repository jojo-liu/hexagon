package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.mapper.PayChannelMapper;
import com.jojoliu.hexagon.model.PayChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jojo on 26/05/2017.
 */

@Service
public class PayChannelServiceImpl {

    @Autowired
    private PayChannelMapper payChannelMapper;

    public List<PayChannel> list() {
        return payChannelMapper.selectAll();
    }
}
