package com.jojoliu.hexagon.model;

import java.util.Date;

public class Session {
    private String sessionId;

    private String accessToken;

    private String refreshToken;

    private String accessTokenValidtime;

    private String refreshTokenTime;

    private Date createTime;

    private Date updateTime;

    private String userId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public String getAccessTokenValidtime() {
        return accessTokenValidtime;
    }

    public void setAccessTokenValidtime(String accessTokenValidtime) {
        this.accessTokenValidtime = accessTokenValidtime == null ? null : accessTokenValidtime.trim();
    }

    public String getRefreshTokenTime() {
        return refreshTokenTime;
    }

    public void setRefreshTokenTime(String refreshTokenTime) {
        this.refreshTokenTime = refreshTokenTime == null ? null : refreshTokenTime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}