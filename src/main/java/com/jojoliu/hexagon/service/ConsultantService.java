package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.common.Page;
import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.Consultant;

/**
 * Created by Jojo on 21/05/2017.
 */
public interface ConsultantService {

    Response<Page<Consultant>> showConsultant(Page<Consultant> page, Long tagid);

}
