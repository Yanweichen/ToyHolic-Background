package com.ywc.mgt.user.service.impl;

import com.ywc.mgt.user.dao.UserMapper;
import com.ywc.mgt.user.model.User;
import com.ywc.mgt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanweichen on 2017/4/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    @Override
    @Transactional()
    public void addUser() {
        User user = new User();
        user.setUserName("asdasd");
        user.setUserPassword("sadasdasdasdas");
        userMapper.insert(user);
        throw new RuntimeException();
    }

}
