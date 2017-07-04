package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.model.Post;

import java.util.List;


/**
 * Created by Jojo on 21/05/2017.
 */

public interface PostService {

    boolean publish(Post post) throws Exception;

    boolean modify(Post post) throws Exception;

    boolean delete(Post post) throws Exception;

    List<Post> getByUser(String userId) throws Exception;

    List<Post> getByConsultant(String tagId, String consultantId) throws Exception;
}
