package com.jojoliu.hexagon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Jojo on 27/05/2017.
 */
public abstract class AbstractBaseService<T> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Mapper<T> mapper;

}
