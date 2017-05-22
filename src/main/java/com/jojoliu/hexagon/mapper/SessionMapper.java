package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Session;

public interface SessionMapper {
    int deleteByPrimaryKey(Long sessionid);

    int insert(Session record);

    int insertSelective(Session record);

    Session selectByPrimaryKey(Long sessionid);

    int updateByPrimaryKeySelective(Session record);

    int updateByPrimaryKey(Session record);
}