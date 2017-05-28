package com.jojoliu.hexagon.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Jojo on 27/05/2017.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
