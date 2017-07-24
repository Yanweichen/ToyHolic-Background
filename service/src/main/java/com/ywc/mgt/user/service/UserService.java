package com.ywc.mgt.user.service;

import com.ywc.mgt.user.model.User;

/**
 * Created by yanweichen on 2017/4/3.
 */
public interface UserService {

    User getUser();

    void addUser() throws Exception;
}
