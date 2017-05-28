package com.jojoliu.hexagon.enums.Order;

/**
 * Created by Jojo on 27/05/2017.
 */

/*
支付事件PAY会触发状态从待支付UNPAID状态到待收货WAITING_FOR_RECEIVE状态的迁移，
而收货事件RECEIVE会触发状态从待收货WAITING_FOR_RECEIVE状态到结束DONE状态的迁移。
*/
public enum Events {
    PAY,        // 支付
    RECEIVE     // 收货
}
