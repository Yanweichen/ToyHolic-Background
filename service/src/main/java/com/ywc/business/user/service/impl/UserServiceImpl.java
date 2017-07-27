package com.ywc.business.user.service.impl;

import com.ywc.business.user.dao.UserMapper;
import com.ywc.business.user.model.User;
import com.ywc.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

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
    public void addUser() {
        User user = new User();
        user.setUserName("asdasd");
        user.setUserPassword("sadasdasdasdas");
        userMapper.insert(user);
    }

    @Override
    public List<User> selectUser(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<User> selectByExample(Example example) {
        return userMapper.selectByExample(example);
    }
}
