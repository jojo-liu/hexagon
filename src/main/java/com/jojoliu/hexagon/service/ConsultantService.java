package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.model.Consultant;

import java.util.List;

/**
 * Created by Jojo on 21/05/2017.
 */
public interface ConsultantService {

    Consultant signIn(String phoneNumber, String verificationCode) throws Exception;

    int updateInfo(Consultant consultant) throws Exception;

    List<Consultant> getConsultant(String tagId, String userId) throws Exception;

}
