package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.model.Post;
import com.jojoliu.hexagon.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jojo on 27/05/2017.
 */

@RestController
@RequestMapping("api/user/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public Response publish(Post post) {
        if(Objects.isNull(post) || Objects.isNull(post.getUserId()) || Objects.isNull(post.getTagId())
                || Objects.isNull(post.getTitle()) || Objects.isNull(post.getSummary())) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            boolean content = postService.publish(post);
            return Response.ok(content);
        }
        catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Response modify(Post post) {
        if(Objects.isNull(post) || Objects.isNull(post.getPostId()) || Objects.isNull(post.getUserId())
                || Objects.isNull(post.getTitle()) || Objects.isNull(post.getSummary())) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            boolean content = postService.modify(post);
            return Response.ok(content);
        }
        catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(Post post) {
        if(Objects.isNull(post) || Objects.isNull(post.getPostId()) || Objects.isNull(post.getUserId())
                || Objects.isNull(post.getTitle()) || Objects.isNull(post.getSummary())) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            boolean content = postService.delete(post);
            return Response.ok(content);
        }
        catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getByUser", method = RequestMethod.POST)
    public Response getByUser(String userId) {
        if(Objects.isNull(userId)) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            List<Post> content = postService.getByUser(userId);
            return Response.ok(content);
        }
        catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getByConsultant", method = RequestMethod.POST)
    public Response getByConsultant(String tagId, String consultantId) {
        if(Objects.isNull(tagId) || Objects.isNull(consultantId)) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            List<Post> content = postService.getByConsultant(tagId, consultantId);
            return Response.ok(content);
        }
        catch (CommonException e) {
            logger.warn(e.getMessage(),e);
            return Response.error(e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Response.error(ErrorCode.SERVER_ERROR);
        }
    }
}
