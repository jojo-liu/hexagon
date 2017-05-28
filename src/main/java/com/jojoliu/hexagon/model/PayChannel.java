package com.jojoliu.hexagon.model;

public class PayChannel {
    private Integer id;

    private String paychannel;

    private String paychannelname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaychannel() {
        return paychannel;
    }

    public void setPaychannel(String paychannel) {
        this.paychannel = paychannel == null ? null : paychannel.trim();
    }

    public String getPaychannelname() {
        return paychannelname;
    }

    public void setPaychannelname(String paychannelname) {
        this.paychannelname = paychannelname == null ? null : paychannelname.trim();
    }
}