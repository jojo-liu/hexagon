package com.jojoliu.hexagon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.mapper.ConsultantMapper;
import com.jojoliu.hexagon.model.Consultant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */
public class ConsultantServiceImpl implements ConsultantService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsultantMapper consultantMapper;

    @Override
    public Response<Page<Consultant>> showConsultant(Page<Consultant> page, Long tagid) {
        Response<Page<Consultant>> response = new Response<Page<Consultant>>();
        try {
            PageHelper.startPage(page.getPageNo(), page.getPageSize());
            List<Consultant> consultants = consultantMapper.selectByTagid(tagid);
            PageInfo<Consultant> temp = new PageInfo<Consultant>(consultants);
            page.setData(temp.getList());
            page.setTotal(temp.getTotal());
            page.setPageMax(temp.getLastPage());
            response.setResult(page);
        } catch (Exception e) {
            logger.error("consultant page fail, tagid = {}, error = {}", tagid, Throwables.getStackTraceAsString(e));
            response.setError("consultant.page.fail");
        }
        return response;
    }
}
