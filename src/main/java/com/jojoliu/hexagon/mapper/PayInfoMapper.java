package com.jojoliu.hexagon.mapper;

import com.jojoliu.hexagon.model.PayInfo;

public interface PayInfoMapper extends tk.mybatis.mapper.common.Mapper<PayInfo>, tk.mybatis.mapper.common.MySqlMapper<PayInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}