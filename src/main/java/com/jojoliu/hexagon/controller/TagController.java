package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.JsonResponseException;
import com.jojoliu.hexagon.model.Tag;
import com.jojoliu.hexagon.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jojo on 27/05/2017.
 */

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("")
    public String list(Model model){
        List<Tag> tags = tagService.list();
        model.addAttribute("categories",tags);
        return "tag/list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Tag tag, Long parentTagid){
        try{
            Long tagid = tag.getTagid();
            if(null == tagid){
                tagService.createTag(tag, parentTagid);
            }else{
                tagService.updateTag(tag);
            }
        }catch (Exception e){
            throw new JsonResponseException(e.getMessage());
        }
        return true;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long tagid){
        try{
            tagService.deleteTag(tagid);
        }catch (Exception e){
            throw new JsonResponseException(e.getMessage());
        }
        return true;
    }
}
