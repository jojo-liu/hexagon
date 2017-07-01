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
        return new OK(content);
    }

    public static Response error(ErrorCode errorCode) {
        return new ERROR(errorCode);
    }

    public static Response error(CommonException exception) {
        return new ERROR(exception);
    }

    public static Response error(int code, String msg) {
        return new ERROR(code, msg);
    }

    public static class OK extends Response {
        public OK(Object content) {
            this.setCode(ErrorCode.OK.getCode());
            this.setContent(content);
        }
    }

    public static class ERROR extends Response {
        public ERROR(ErrorCode errorCode) {
            this.setCode(errorCode.getCode());
            this.setMsg(errorCode.getMsg());
        }

        public ERROR(int code, String msg) {
            this.setCode(code);
            this.setMsg(msg);
        }

        public ERROR(CommonException exception) {
            this.setCode(exception.getCode());
            this.setMsg(exception.getMessage());
        }
    }

}
