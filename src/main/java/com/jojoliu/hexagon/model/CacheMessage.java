package com.jojoliu.hexagon.model;

import com.jojoliu.hexagon.util.CacheCorrelationData;

/**
 * Created by Jojo on 24/05/2017.
 */
public class CacheMessage {
    private Object payload;
    private CacheCorrelationData cacheCorrelationData;

    public Object getPayload() {
        return payload;
    }
    public void setPayload(Object payload) {
        this.payload = payload;
    }
    public CacheCorrelationData getCacheCorrelationData() {
        return cacheCorrelationData;
    }
    public void setCacheCorrelationData(CacheCorrelationData cacheCorrelationData) {
        this.cacheCorrelationData = cacheCorrelationData;
    }
}
