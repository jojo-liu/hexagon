package com.jojoliu.hexagon.model;

import java.util.Date;

public class Order {
    private Long orderid;

    private Date createtime;

    private Long price;

    private Long postid;

    private Long consultantid;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
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
}