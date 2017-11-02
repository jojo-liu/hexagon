package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.mapper.ConsultantMapper;
import com.jojoliu.hexagon.mapper.PostMapper;
import com.jojoliu.hexagon.mapper.TagMapper;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.Post;
import com.jojoliu.hexagon.util.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jojo on 21/05/2017.
 */

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConsultantMapper consultantMapper;

    @Autowired
    private TagMapper tagMapper;

    public boolean publish(Post post) throws Exception{
        //进行有效性校验
        //检查userId, tagId
        if(Objects.isNull(userMapper.selectByPrimaryKey(post.getUserId()))) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }
        if(Objects.isNull((tagMapper.selectByPrimaryKey(post.getTagId())))) {
            throw new CommonException(ErrorCode.TAG_ERROR);
        }
        if(Objects.isNull(post.getCreateTime())) {
            post.setCreateTime(new Date());//设置创建时间
            post.setUpdateTime(new Date());//设置更新时间
        }
        post.setPostId(UUidUtil.generate());//设置postId

        int result = postMapper.insert(post);
        if(result == 1) {
            //需求发布成功
            post.setPostStatus(3);//设置当前的post状态为发布成功
            postMapper.updateByPrimaryKey(post);
            //此时，需要同步生成订单并支付
            return true;
        }
        else {
            post.setPostStatus(1);//设置当前的post状态为待发布
            postMapper.updateByPrimaryKey(post);
            return false;
        }
    }

    @Override
    public boolean modify(Post post) throws Exception{
        //进行有效性校验
        //检查userId, tagId
        if(Objects.isNull(userMapper.selectByPrimaryKey(post.getUserId()))) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }
        Post postInDatabase = postMapper.selectByPrimaryKey(post.getPostId());
        if(Objects.isNull(postInDatabase)) {
            throw new CommonException((ErrorCode.POST_ERROR));
        }
        if(postInDatabase.getUserId() != post.getUserId()) {
            throw new CommonException(ErrorCode.ILLEGAL_ERROR);//当前用户不拥有此条需求记录，非法修改
        }
        if(Objects.isNull((tagMapper.selectByPrimaryKey(post.getTagId())))) {
            throw new CommonException(ErrorCode.TAG_ERROR);
        }

        int result = postMapper.updateByPrimaryKey(post);
        if(result == 1) {//因为之前判断过post的id是有效的，这里如果返回0就表示没有修改
            return true;
        }
        else {
            return false;//新的需求和原需求记录相同，没有修改
        }
    }

    @Override
    public boolean delete(Post post) throws Exception{
        //进行有效性校验
        //检查userId, tagId是否为空，并且判断当前post为当前用户所拥有
        if(Objects.isNull(userMapper.selectByPrimaryKey(post.getUserId()))) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }
        Post postInDatabase = postMapper.selectByPrimaryKey(post.getPostId());
        if(Objects.isNull(postInDatabase)) {
            throw new CommonException((ErrorCode.POST_ERROR));
        }
        if(postInDatabase.getUserId() != post.getUserId()) {
            throw new CommonException(ErrorCode.ILLEGAL_ERROR);//当前用户不拥有此条需求记录，非法修改
        }
        if(Objects.isNull((tagMapper.selectByPrimaryKey(post.getTagId())))) {
            throw new CommonException(ErrorCode.TAG_ERROR);
        }

        int result = postMapper.deleteByPrimaryKey(post.getPostId());
        if(result == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Post> getByUser(String userId) throws Exception {
        //进行有效性校验
        if(Objects.isNull(userMapper.selectByPrimaryKey(userId))) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }
        List<Post> result = postMapper.selectByUserId(userId);
        return result;
    }

    @Override
    public List<Post> getByConsultant(String tagId, String consultantId) throws Exception {
        //进行有效性校验
        if(Objects.isNull(consultantMapper.selectByPrimaryKey(consultantId))) {
            throw new CommonException(ErrorCode.CONSULTANT_INFO_ERROR);
        }
        if(Objects.isNull(tagMapper.selectByPrimaryKey(tagId))) {
            throw new CommonException(ErrorCode.TAG_ERROR);
        }
        List<Post> result = postMapper.selectByConsultant(tagId);
        return result;
    }
}
