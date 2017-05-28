package com.jojoliu.hexagon.controller;

import com.jojoliu.hexagon.common.JsonResponseException;
import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.Post;
import com.jojoliu.hexagon.model.Tag;
import com.jojoliu.hexagon.model.User;
import com.jojoliu.hexagon.service.PostQuery;
import com.jojoliu.hexagon.service.PostService;
import com.jojoliu.hexagon.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Jojo on 27/05/2017.
 */

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private TagService tagService;

    @Autowired
    private PostService postService;

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
