package com.jojoliu.hexagon.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 7028505176571583916L;

    private List<T> data;

    private Long total;

    private Integer pageMax = Integer.valueOf(1);

    private Boolean isEmpty = Boolean.valueOf(true);

    private Integer pageNo = Integer.valueOf(1);

    private Integer pageSize = Integer.valueOf(20);

    public Page(){
    }

    public Page(List data){
        this.data = data;
        if(data != null && data.size() > 0){
            this.isEmpty = Boolean.valueOf(false);
        }
    }

    public Page(List data, Integer total){
        this.data = data;
        if(data != null && data.size() > 0) {
            this.isEmpty = Boolean.valueOf(false);
        }
        this.total = Long.valueOf(total);
        Double temp = Math.floor(total.doubleValue()/pageSize);
        this.pageMax = temp.intValue();
    }

    public Integer getOffset() {
        Integer offset = Integer.valueOf((this.pageNo.intValue() - 1) * this.pageSize.intValue());
        return Integer.valueOf(offset.intValue() > 0?offset.intValue():0);
    }

    public Integer getLimit() {
        return Integer.valueOf(this.pageSize.intValue() > 0?this.pageSize.intValue():20);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        if(data!=null&&data.size()>0){
            this.isEmpty = Boolean.valueOf(false);
        }
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageMax() {
        return pageMax;
    }

    public void setPageMax(Integer pageMax) {
        this.pageMax = pageMax;
    }
}
