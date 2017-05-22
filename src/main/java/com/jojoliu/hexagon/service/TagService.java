package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.model.Tag;

import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */
public interface TagService {
    List<Tag> list();

    List<Tag> listSubTags(Long tagid);

    void createTag(Tag tag);

    void deleteTag(Long tagid);

    void updateTag(Tag tag);
}
