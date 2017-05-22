package com.jojoliu.hexagon.common;

import java.io.Serializable;

/**
 * Created by Jojo on 21/05/2017.
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 3754539662039951920L;

    private Boolean success = Boolean.valueOf(false);

    private String error;

    private T result;

    public Response() {
    }

    public void setError(String error) {
        this.error = error;
        this.success = Boolean.valueOf(false);
    }

    public void setResult(T t) {
        this.result = t;
        this.success = Boolean.valueOf(true);
    }

    public Boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public T getResult() {
        return result;
    }
}
