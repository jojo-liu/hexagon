package com.jojoliu.hexagon.service;

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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> list() {
        List<Tag> tags = tagMapper.selectAll();
        return tags;
    }

    @Override
    public List<Tag> listSubTags(Long tagid) {
        List<Tag> tags = tagMapper.selectByParent(tagid);
        return tags;
    }

    @Override
    public void createTag(Tag tag, Long parentTagid) {
        tag.setParent(parentTagid);
        tagMapper.insert(tag);
    }

    @Override
    public void deleteTag(Long tagid) {
        tagMapper.deleteByPrimaryKey(tagid);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateByPrimaryKeySelective(tag);
    }
}
