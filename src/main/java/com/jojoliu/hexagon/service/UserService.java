package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.model.User;


/**
 * Created by Jojo on 20/05/2017.
 */

public interface UserService {
    User signIn(String phoneNumber, String verificationCode) throws Exception;

    int updateInfo(User user) throws Exception;

    boolean sendSms(String phoneNumber) throws Exception;
}
