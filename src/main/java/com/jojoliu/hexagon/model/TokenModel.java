package com.jojoliu.hexagon.model;

/**
 * Created by Jojo on 04/07/2017.
 */
public class TokenModel {
    // 用户 id，这里的id包括userId和consultantId，后期会使用泛型来支持User和Consultant
    // 用户 id
    private String userId;
    // 随机生成的 uuid
    private String token;
    public TokenModel (String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
    public String getUserId () {
        return userId;
    }
    public void setUserId (String userId) {
        this.userId = userId;
    }
    public String getToken () {
        return token;
    }
    public void setToken (String token) {
        this.token = token;
    }
}
