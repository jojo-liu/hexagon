package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long tagid);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long tagid);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectAll();

    List<Tag> selectByParent(Long tagid);
}