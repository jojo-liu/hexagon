package com.jojoliu.hexagon.common;

import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;

import java.io.Serializable;

/**
 * Created by Jojo on 21/05/2017.
 */
public class Response implements Serializable {

    private int code;

    private String msg;

    private Object content;

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public static Response ok(Object content) {
        return new Ok(content);
    }

    public static Response error(ErrorCode errorCode) {
        return new Error(errorCode);
    }

    public static Response error(CommonException exception) {
        return new Error(exception);
    }

    public static Response error(int code, String msg) {
        return new Error(code, msg);
    }

    public static class Ok extends Response {
        public Ok(Object content) {
            this.setCode(ErrorCode.OK.getCode());
            this.setMsg(ErrorCode.OK.getMsg());
            this.setContent(content);
        }
    }

    public static class Error extends Response {
        public Error(ErrorCode errorCode) {
            this.setCode(errorCode.getCode());
            this.setMsg(errorCode.getMsg());
        }

        public Error(int code, String msg) {
            this.setCode(code);
            this.setMsg(msg);
        }

        public Error(CommonException exception) {
            this.setCode(exception.getCode());
            this.setMsg(exception.getMessage());
        }
    }

}
