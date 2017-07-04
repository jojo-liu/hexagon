package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Session;

public interface SessionMapper {
    int deleteByPrimaryKey(String sessionId);

    int insert(Session record);

    int insertSelective(Session record);

    Session selectByPrimaryKey(String sessionId);

    int updateByPrimaryKeySelective(Session record);

    int updateByPrimaryKey(Session record);
}