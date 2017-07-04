package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.controller.UserController;
import com.jojoliu.hexagon.mapper.TagMapper;
import com.jojoliu.hexagon.model.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */

@Service
public class TagServiceImpl implements TagService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> showAll() throws Exception {
        List<Tag> tags = tagMapper.selectAll();
        return tags;
    }

    @Override
    public List<Tag> showSubTag(String tagId) throws Exception{
        List<Tag> tags = tagMapper.selectByParent(tagId);
        return tags;
    }
}
