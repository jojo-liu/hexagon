package com.jojoliu.hexagon.enums;

/**
 * Created by Jojo on 09/07/2017.
 */
public enum ConsultantOrderStatus {
    Init("初始状态", -1),
    MissedOrder("未接单", 0),
    FailedOrder("接单失败", 1),
    SuccessfulOrder("接单成功", 2),
    ReceivedPay("已收款", 3);

    private String description;
    private int idx;

    ConsultantOrderStatus(String description, int idx) {
        this.description = description;
        this.idx = idx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    //普通方法，根据idx找到状态描述description
    public static String getStatus(int idx) {
        for (ConsultantOrderStatus consultantOrderStatus : ConsultantOrderStatus.values()) {
            if (consultantOrderStatus.getIdx() == idx) {
                return consultantOrderStatus.description;
            }
        }
        return null;
    }
}
