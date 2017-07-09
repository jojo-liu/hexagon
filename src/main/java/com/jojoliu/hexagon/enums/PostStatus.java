package com.jojoliu.hexagon.enums;

/**
 * Created by s on 17-7-9.
 *
 * 需求状态类型
 */


public enum PostStatus {

    Init("初始状态", -1),
    Rescinded("已撤销", 0),
    PendingPublish("待发布",1),
    PublishFail("发布失败", 2),
    PublishSuccess("发布成功",3),
    PendingPay("待支付",4),
    MissedOrder("未接单",5),
    ReceivedOrder("已接单",6),
    Completed("已完成",7)
    ;

    private String msg;
    private int idx;

    PostStatus(String msg, int idx) {
        this.msg = msg;
        this.idx = idx;
    }

    public String getStatusMsg() {
        return msg;
    }

    public int getStatusIdx() {
        return idx;
    }
}
