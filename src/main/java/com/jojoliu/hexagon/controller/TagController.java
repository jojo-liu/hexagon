package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.model.Tag;
import com.jojoliu.hexagon.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jojo on 27/05/2017.
 */

@RestController
@RequestMapping("/api/tag")
public class TagController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TagService tagService;

    @RequestMapping("/showAll")
    public Response showAll(){
        try {
            List<Tag> tags = tagService.showAll();
            return Response.ok(tags);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/showSubTag")
    public Response showSubTag(String tagId) {
        if(Objects.isNull(tagId)) {
            return Response.error(ErrorCode.TAG_ERROR);
        }
        try {
            List<Tag> tags = tagService.showSubTag(tagId);
            return Response.ok(tags);
        } catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }
}
