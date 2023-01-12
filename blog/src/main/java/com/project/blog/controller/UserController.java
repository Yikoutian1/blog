package com.project.blog.controller;

import com.project.blog.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.blog.controller.BaseController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Qiulihang
 * @since 2023-01-12
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

}
