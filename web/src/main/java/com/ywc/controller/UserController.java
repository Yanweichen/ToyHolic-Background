package com.ywc.controller;

import com.github.pagehelper.PageHelper;
import com.ywc.common.qcloud.file.FileManager;
import com.ywc.common.qcloud.model.FileResult;
import com.ywc.dao.AuthorityMapper;
import com.ywc.mgt.user.dao.UserMapper;
import com.ywc.mgt.user.model.User;
import com.ywc.mgt.user.service.UserService;
import com.ywc.model.dto.AuthorityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @Autowired
    private AuthorityMapper authorityMapper;

    @GetMapping("/userAuth/{id}")
    public List<AuthorityDTO> getUser(@PathVariable("id") Integer id){
        return authorityMapper.selectRoleAuthority(id);
    }

    @GetMapping("/roleAuth/{id}")
    public List<AuthorityDTO> getAuth(@PathVariable("id") Integer id){
        return authorityMapper.selectUserAuthority(id);
    }

    @GetMapping("/list")
    public List<User> getUserList(int page){
        PageHelper.startPage(page,5);
        List<User> users = userMapper.selectAll();
        return users;
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

    @PostMapping("/upload")
    public FileResult upload(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            return FileManager.upload(file.getBytes(),"/test/"+file.getOriginalFilename());
        }
        return null;
    }

    @DeleteMapping("/delete")
    public FileResult delete() throws IOException {
        return FileManager.delete("/test/123.png");
    }
}
