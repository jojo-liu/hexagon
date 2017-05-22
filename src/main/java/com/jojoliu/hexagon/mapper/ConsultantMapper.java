package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Consultant;

import java.util.List;

public interface ConsultantMapper {
    int deleteByPrimaryKey(Long consultantid);

    int insert(Consultant record);

    int insertSelective(Consultant record);

    Consultant selectByPrimaryKey(Long consultantid);

    int updateByPrimaryKeySelective(Consultant record);

    int updateByPrimaryKey(Consultant record);

    List<Consultant> selectByTagid(Long tagid);
}