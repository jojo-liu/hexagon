package com.jojoliu.hexagon.service;

import com.jojoliu.hexagon.common.Response;
import com.jojoliu.hexagon.model.User;


/**
 * Created by Jojo on 20/05/2017.
 */

public interface UserService {
    Response<User> login(String username, String password);

    Response<User> register(User user);

    void update(User user);
}
