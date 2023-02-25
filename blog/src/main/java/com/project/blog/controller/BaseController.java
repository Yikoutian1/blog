package com.project.blog.controller;

import com.project.blog.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/1/12 21:34
 * @Version 1.0
 */

@RestController
public class BaseController {
    /**
     * 在基类中注入
     */
    @Autowired
    UserServiceImpl userService;

}
