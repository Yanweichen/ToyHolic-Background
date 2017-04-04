package com.ywc.controller;

import com.ywc.mgt.user.dao.UserMapper;
import com.ywc.mgt.user.model.User;
import com.ywc.mgt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanweichen on 2017/4/3.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public User getUser(){
        User user = userService.getUser();
        return user;
    }

    @GetMapping("/add")
    public String addUser(){
        try {
            userService.addUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
