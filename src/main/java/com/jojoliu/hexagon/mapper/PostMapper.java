package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Post;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Long postid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long postid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> selectByTagid(Long tagid);

    List<Post> selectByUserid(Long userid);
}