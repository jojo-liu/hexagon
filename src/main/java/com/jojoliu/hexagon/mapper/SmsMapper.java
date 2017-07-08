package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Sms;

public interface SmsMapper {
    int deleteByPrimaryKey(String smsId);

    int insert(Sms record);

    int insertSelective(Sms record);

    Sms selectByPrimaryKey(String smsId);

    int updateByPrimaryKeySelective(Sms record);

    int updateByPrimaryKey(Sms record);

    int deleteByPhoneNumber(String phoneNumber);

    Sms selectByPhoneNumber(String phoneNumber);
}