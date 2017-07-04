package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.model.PayInfo;

import java.util.List;

/**
 * Created by Jojo on 26/05/2017.
 */
public interface PayInfoService {

    List<PayInfo> list();
    List<PayInfo> find(PayInfo payInfo);
    PayInfo add(PayInfo payInfo);

//    Response pay(PayInfo payInfo);
    boolean payMock();
    int delete(Long id);
}
