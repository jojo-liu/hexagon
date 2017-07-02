package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.JsonResponseException;
import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.model.Post;
import com.jojoliu.hexagon.model.Tag;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.service.PostQuery;
import com.jojoliu.hexagon.service.PostService;
import com.jojoliu.hexagon.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jojo on 27/05/2017.
 */

@RestController
@RequestMapping("/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TagService tagService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/api/user/post/publish", method = RequestMethod.POST)
    public Response publish(Post post) {
        logger.info("PostController#publish,post:{}", post);
        if(Objects.isNull(post) || Objects.isNull(post.getUserid()) || Objects.isNull(post.getTagid())
                || Objects.isNull(post.getTitle())) {
            return Response.error(ErrorCode.PARAM_ERROR);
        }
        try {
            int content = postService.publish(post);
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

    @RequestMapping("")
    public String page(Model model, Page<Post> page, PostQuery query, HttpSession session){
        User user = (User)session.getAttribute("user");
        query.setUserid(user.getUserid());
        Response<Page<Post>> response = postService.getPostByUserid(page,query);
        if(!response.isSuccess()){
            throw new JsonResponseException(response.getError());
        }
        //通过model向页面传值
        model.addAttribute("page",response.getResult());
        List<Tag> tags = tagService.list();
        model.addAttribute("tags",tags);
        return "post/page";
    }

    @RequestMapping("/edit")
    public String update(Model model, @RequestParam(required = false) Long postid, HttpSession session){
        Post post = new Post();
        if(null != postid){
            User user = (User)session.getAttribute("user");
            Response<Post> response = postService.get(postid);
            if(!response.isSuccess()){
                throw new JsonResponseException(response.getError());
            }
            post = response.getResult();
        }
        model.addAttribute("post",post);
        List<Tag> tags = tagService.list();
        model.addAttribute("tags",tags);
        return "post/edit";
    }

    @RequestMapping("/save")
    public Object create(Post post, HttpSession session) {
        try{
            Long postid = post.getPostid();
            if(postid == null){
                User user = (User)session.getAttribute("user");
                post.setUserid(user.getUserid());
                post.setCreatetime(new Date());
                post.setUpdatetime(new Date());
                postService.createPost(post);
            }else {
                post.setUpdatetime(new Date());
                postService.updatePost(post);
            }
        }catch (Exception e){
            throw new JsonResponseException(e.getMessage());
        }
        return true;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long postid) {
        postService.deletePost(postid);
        return true;
    }
}
