package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.Post;

import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(String postId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> selectByConsultant(String tagId);

    List<Post> selectByUserId(String userId);
}