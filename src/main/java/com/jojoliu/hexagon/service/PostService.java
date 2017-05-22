package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.Post;


/**
 * Created by Jojo on 21/05/2017.
 */

public interface PostService {
    void createPost(Post post);

    void deletePost(Long postid);

    void updatePost(Post post);

    Response<Page<Post>> getPostByUserid(Page<Post> page, PostQuery query);

    Response<Page<Post>> getPostByTagid(Page<Post> page, PostQuery query);

//    boolean isExist(String url);

//    Response<Map<String, Post>> getPrevAndNextBlog(Long postid);
}
