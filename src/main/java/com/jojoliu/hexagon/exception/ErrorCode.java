package com.jojoliu.hexagon.exception;

/**
 * Created by huangzhaolong on 2017/7/2.
 */
public enum ErrorCode {

    OK(200, "success"),

    SERVER_ERROR(10000, "服务器异常"),
    PARAM_ERROR(10001, "请求参数不正确"),
    USERINFO_ERROR(10002, "用户数据不存在"),
    //自己随意添加错误类型
    ;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    int code;
    String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
