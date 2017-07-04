package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.mapper.TestMapper;
import com.jojoliu.hexagon.model.Test;
import com.jojoliu.hexagon.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangzhaolong on 2017/5/16.
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public void test() {
        Test test = testMapper.selectByPrimaryKey(1);
        LogUtil.logInfo("test name is " + test.getName());
    }
}
