package com.jojoliu.hexagon.model;

import java.util.Date;

public class Order {
    private Long orderid;

    private String orderno;

    private Date createtime;

    private Long price;

    private Long postid;

    private Long consultantid;

    private String status;

    private String paychannel;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPostid() {
        return postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public Long getConsultantid() {
        return consultantid;
    }

    public void setConsultantid(Long consultantid) {
        this.consultantid = consultantid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPaychannel() {
        return paychannel;
    }

    public void setPaychannel(String paychannel) {
        this.paychannel = paychannel == null ? null : paychannel.trim();
    }
}