package com.jojoliu.hexagon.model;

import java.util.Date;

public class Order {
    private String orderId;

    private String orderNo;

    private Date createTime;

    private Integer price;

    private String postId;

    private String consultantId;

    private String payChannel;

    private Integer userOrderStatus;

    private Integer consultantOrderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId == null ? null : consultantId.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public Integer getUserOrderStatus() {
        return userOrderStatus;
    }

    public void setUserOrderStatus(Integer userOrderStatus) {
        this.userOrderStatus = userOrderStatus;
    }

    public Integer getConsultantOrderStatus() {
        return consultantOrderStatus;
    }

    public void setConsultantOrderStatus(Integer consultantOrderStatus) {
        this.consultantOrderStatus = consultantOrderStatus;
    }
}