package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.exception.CommonException;
import com.jojoliu.hexagon.exception.ErrorCode;
import com.jojoliu.hexagon.mapper.ConsultantMapper;
import com.jojoliu.hexagon.mapper.TagMapper;
import com.jojoliu.hexagon.mapper.UserMapper;
import com.jojoliu.hexagon.model.Consultant;
import com.jojoliu.hexagon.model.Tag;
import com.jojoliu.hexagon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Jojo on 21/05/2017.
 */

@Service
public class ConsultantServiceImpl implements ConsultantService {

    @Autowired
    private ConsultantMapper consultantMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Consultant signIn(String phoneNumber, String verificationCode) throws Exception {
        //从数据库中获取用户信息
        Consultant consultant = consultantMapper.selectByPrimaryKey(phoneNumber);
//        checkArgument(user != null, "用户名不存在");
//        String realPassword = user.getPassword();
//        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
//        checkArgument(Objects.equal(md5Password, realPassword), "密码错误");
        return consultant;
    }

    @Override
    public int updateInfo(Consultant consultant) throws Exception {
        //实现有效性校验
        Consultant resultConsultant = consultantMapper.selectByPrimaryKey(consultant.getConsultantId());
        if(Objects.isNull(resultConsultant)) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }
        int result = consultantMapper.updateByPrimaryKey(consultant);
        return result;
    }

    @Override
    public List<Consultant> getConsultant(String tagId, String userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        if(Objects.isNull(user)) {
            throw new CommonException(ErrorCode.USER_INFO_ERROR);
        }

        Tag tag = tagMapper.selectByPrimaryKey(tagId);
        if(Objects.isNull(tag)) {
            throw new CommonException(ErrorCode.TAG_ERROR);
        }
        List<Consultant> result = consultantMapper.selectByTagId(tagId);
        return result;
    }
}
