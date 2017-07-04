package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.model.Tag;

import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */
public interface TagService {
    List<Tag> showAll() throws Exception;

    List<Tag> showSubTag(String tagId) throws Exception;
}
