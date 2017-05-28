package com.jojoliu.hexagon.enums;

/**
 * Created by Jojo on 25/05/2017.
 */
public enum OrderStatus {
    I("初始"),
    P("处理中"),
    S("成功"),
    F("失败");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
