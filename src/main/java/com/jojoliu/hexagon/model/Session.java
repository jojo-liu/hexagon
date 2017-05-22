package com.jojoliu.hexagon.model;

import java.util.Date;

public class Session {
    private Long sessionid;

    private String accesstoken;

    private String refreshtoken;

    private String accesstokenvalidtime;

    private String refreshtokentime;

    private Date createtime;

    private Date updatetime;

    private Long userid;

    public Long getSessionid() {
        return sessionid;
    }

    public void setSessionid(Long sessionid) {
        this.sessionid = sessionid;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken == null ? null : refreshtoken.trim();
    }

    public String getAccesstokenvalidtime() {
        return accesstokenvalidtime;
    }

    public void setAccesstokenvalidtime(String accesstokenvalidtime) {
        this.accesstokenvalidtime = accesstokenvalidtime == null ? null : accesstokenvalidtime.trim();
    }

    public String getRefreshtokentime() {
        return refreshtokentime;
    }

    public void setRefreshtokentime(String refreshtokentime) {
        this.refreshtokentime = refreshtokentime == null ? null : refreshtokentime.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}