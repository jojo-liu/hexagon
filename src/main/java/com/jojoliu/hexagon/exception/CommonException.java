package com.jojoliu.hexagon.exception;

/**
 * Created by huangzhaolong on 2017/7/2.
 */
public class CommonException extends Exception {

    private final int code;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
