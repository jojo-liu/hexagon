package com.jojoliu.hexagon.common;

/**
 * Created by Jojo on 27/05/2017.
 */
public class JsonResponseException extends RuntimeException {private int status = 500;
    private String message = "unknow error";

    public JsonResponseException() {
    }

    public JsonResponseException(String message) {
        this.message = message;
    }

    public JsonResponseException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
