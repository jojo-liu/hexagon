package com.jojoliu.hexagon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.mapper.PostMapper;
import com.jojoliu.hexagon.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */

@Service
public class PostServiceImpl implements PostService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostMapper postMapper;

    public void createPost(Post post) {
        postMapper.insert(post);
//        BlogStatistics blogStatistics = new BlogStatistics(blog.getId());
//        blogStatisticsMapper.insert(blogStatistics);
    }

    public void deletePost(Long postid) {
        postMapper.deleteByPrimaryKey(postid);
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public Response<Page<Post>> getPostByUserid(Page<Post> page, PostQuery query) {
        Response<Page<Post>> response = new Response<Page<Post>>();
        try{
            PageHelper.startPage(page.getPageNo(), page.getPageSize());
            List<Post> posts = postMapper.selectByUserid(query.getUserid());
            PageInfo<Post> temp = new PageInfo<Post>(posts);
            page.setData(temp.getList());
            page.setTotal(temp.getTotal());
            page.setPageMax(temp.getLastPage());
            response.setResult(page);
        }catch (Exception e){
            logger.error("post page fail, params = {}, query = {}", query, Throwables.getStackTraceAsString(e));
            response.setError("post.page.fail");
        }
        return response;
    }

    @Override
    public Response<Page<Post>> getPostByTagid(Page<Post> page, PostQuery query) {
        Response<Page<Post>> response = new Response<Page<Post>>();
        try{
            PageHelper.startPage(page.getPageNo(), page.getPageSize());
            List<Post> posts = postMapper.selectByTagid(query.getTagid());
            PageInfo<Post> temp = new PageInfo<Post>(posts);
            page.setData(temp.getList());
            page.setTotal(temp.getTotal());
            page.setPageMax(temp.getLastPage());
            response.setResult(page);
        }catch (Exception e){
            logger.error("post page fail, params = {}, query = {}", query, Throwables.getStackTraceAsString(e));
            response.setError("post.page.fail");
        }
        return response;
    }

//    @Override
//    public boolean isExist(String url) {
//        if(StringUtils.isNotEmpty(url)){
//            Example example = new Example(Post.class);
//            Example.Criteria criteria = example.createCriteria();
//            criteria.andEqualTo("sourceUrl",url);
//            List<Post> posts =  mapper.selectByExample(example);
//            if(posts != null && posts.size()>0){
//                return true;
//            }
//        }
//        return false;
//    }

//    @Override
//    public Response<Map<String, Post>> getPrevAndNextBlog(Long postid) {
//        Response<Map<String, Post>> response = new Response<Map<String, Post>>();
//        Map<String, Post> map = new HashMap<String, Post>();
//        try {
//            PostMapper postMapper = (PostMapper) mapper;
//            List<Post> posts = postMapper.getPrevAndNextBlog(id);
//            if(posts != null && posts.size()>0){
//                for(Post temp : posts){
//                    Long tempId = temp.getPostid();
//                    if(tempId > postid){
//                        map.put("next",temp);
//                    }else{
//                        map.put("prev",temp);
//                    }
//                }
//            }
//            response.setResult(map);
//        } catch (Exception e){
//            logger.error("post get fail, postid = {}, error = {}", postid, Throwables.getStackTraceAsString(e));
//            response.setError("post.get.fail");
//        }
//        return response;
//    }
}
