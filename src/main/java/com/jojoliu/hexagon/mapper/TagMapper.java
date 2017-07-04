package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(String tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(String tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectAll();

    List<Tag> selectByParent(String tagId);
}