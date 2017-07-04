package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Consultant;

import java.util.List;

public interface ConsultantMapper {
    int deleteByPrimaryKey(String consultantId);

    int insert(Consultant record);

    int insertSelective(Consultant record);

    Consultant selectByPrimaryKey(String consultantId);

    int updateByPrimaryKeySelective(Consultant record);

    int updateByPrimaryKey(Consultant record);

    List<Consultant> selectByTagId(String tagId);
}