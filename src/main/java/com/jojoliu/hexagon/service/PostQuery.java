package com.jojoliu.hexagon.service;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Jojo on 22/05/2017.
 */

@Service
public class PostQuery implements Serializable {
    private static final long serialVersionUID = -7585199598535442193L;

    private static final String DEFAUTL_DATE_FORMATTER= "yyyy-MM-dd";

    private String dateStr;

    private Date beginTime;

    private Date endTime;

    private Long userid;

    private Long tagid;

    public PostQuery() {
        super();
    }

    public PostQuery(Long tagid, Long userid, String dateStr) {
        this.tagid = tagid;
        this.userid = userid;
        this.dateStr = dateStr;
        try {
            this.beginTime = DateUtils.parseDate(dateStr,DEFAUTL_DATE_FORMATTER);
            this.endTime = DateUtils.addSeconds(DateUtils.addDays(beginTime,1),-1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public PostQuery(Long tagid, Long userid, Date beginTime, Date endTime) {
        this.tagid = tagid;
        this.userid = userid;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getTagid() {
        return tagid;
    }

    public void setTagid(Long tagid) {
        this.tagid = tagid;
    }
}
