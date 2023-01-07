package com.project.blog.controller;

import com.project.blog.entity.User;
import com.project.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName controller
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/1/5 16:25
 * @Version 1.0
 */
@RestController
public class controller {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/blog")
    public String blog(@RequestParam("test") String test){
        return "blog" + test;
    }
    @GetMapping("/list")
    public List<User> list(){
       return userMapper.selectList(null);
    }
}
