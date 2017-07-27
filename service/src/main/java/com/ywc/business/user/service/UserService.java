package com.ywc.business.user.service;

import com.ywc.business.user.model.User;
import com.ywc.common.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by yanweichen on 2017/4/3.
 */
public interface UserService extends BaseService<User> {

    User getUser();

    void addUser() throws Exception;

    List<User> selectUser(Map<String,Object> map);
}
