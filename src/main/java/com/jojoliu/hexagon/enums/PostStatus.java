package com.jojoliu.hexagon.enums;

/**
 * Created by Jojo on 09/07/2017.
 */
public enum PostStatus {
    Init("初始状态", -1),
    Rescinded("已撤销", 0),
    PendingPublish("待发布",1),
    FailedPublish("发布失败", 2),
    SuccessfulPublish("发布成功",3),
    PendingPay("待支付",4),
    MissedOrder("未接单",5),
    ReceivedOrder("已接单",6),
    Completed("已完成",7)
    ;

    private String description; //状态描述
    private int idx;

    PostStatus(String description, int idx) {
        this.description = description;
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getMsg() {
        return description;
    }

    public void setMsg(String description) {
        this.description = description;
    }

    //普通方法，根据idx找到状态描述description
    public static String getStatus(int idx) {
        for (PostStatus postStatus : PostStatus.values()) {
            if (postStatus.getIdx() == idx) {
                return postStatus.description;
            }
        }
        return null;
    }
}
