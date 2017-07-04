package com.jojoliu.hexagon.exception;

/**
 * Created by huangzhaolong on 2017/7/2.
 */
public enum ErrorCode {

    OK(200, "success"),

    SERVER_ERROR(10000, "服务器异常"),
    PARAM_ERROR(10001, "请求参数不正确"),
    USER_INFO_ERROR(10002, "用户数据不存在"),
    TAG_ERROR(10003, "标签不存在"),
    ILLEGAL_ERROR(10004, "当前用户在执行非法操作"),
    POST_ERROR(10005, "需求信息不存在"),
    CONSULTANT_INFO_ERROR(10006, "专家数据不存在");
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
