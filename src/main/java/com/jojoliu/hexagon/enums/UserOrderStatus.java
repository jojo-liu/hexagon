package com.jojoliu.hexagon.enums;

/**
 * Created by Jojo on 09/07/2017.
 */
public enum UserOrderStatus {
    Init("初始状态", -1),
    Published("已发布", 0),
    CheckOut("已结账", 1);

    private String description;
    private int idx;

    UserOrderStatus(String description, int idx) {
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
        for (UserOrderStatus userOrderStatus : UserOrderStatus.values()) {
            if (userOrderStatus.getIdx() == idx) {
                return userOrderStatus.description;
            }
        }
        return null;
    }
}
