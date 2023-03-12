package com.project.blog.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.UserQuery;
import com.project.blog.entity.User;
import com.project.blog.enums.RoleType;
import com.project.blog.exception.CustomException;
import com.project.blog.utils.JwtUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.project.blog.controller.BaseController;

import java.util.HashMap;
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
    /**
     * list
     * @return
     */
    @GetMapping("/")
    public List<User> list(){
        return userService.list();
    }

    /**
     * page
     * @param userQuery
     * @return
     */
    @PostMapping("/page")
    public Result<?> findPage(@RequestBody UserQuery userQuery){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        /**
         * 通过ID降序排列
         */
        // 特性: jdk8中使用了::的用法。就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的“方法引用”
        wrapper.orderByDesc(User::getId);

        // 如果满足则进行名字的模糊匹配
        // StringUtils.isBlank：判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
        if(!StringUtils.isBlank(userQuery.getUsername())){
            // 模糊匹配我们的Username
            wrapper.like(User::getUsername,userQuery.getUsername());
        }

        // 为什么要分页，因为如果数据多了，那么不可能一次性全部查询的
        //page(xx,yy)  xx:分页对象 yy:查询条件
        Page<User> page = userService.page(new Page<>(
                // 分页信息
                        userQuery.getPageNum(),
                        userQuery.getPageSize()),
                // 分页条件
                wrapper
        );
        // 返回结果
        return Result.success(page);
    }

    /**
     * save
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Object save(@Validated @RequestBody User user) {

        Integer userId = JwtUtils.getCurrentUserInfo();
        if (userId != null) {

            //普通用户
            if (RoleType.ROLE_USER.equals(user.getRoleType())) {
                if (!user.getId().equals(userId)) {
                    throw new CustomException("用户ID不相等，请重新登录后尝试");
                }
            }
            User userInfo = userService.getById(userId);

            if(userInfo != null) {
                //超级管理员
                if (RoleType.ROLE_ADMIN.equals(user.getRoleType()) && user.getRoleType().equals(userInfo.getRoleType())) {
                    userService.saveOrUpdate(user);
                }else{
                    throw new CustomException("用户权限与数据库不一致");
                }
            }else {
                throw new CustomException("用户未找到");
            }

            return Result.success();
        }
        return Result.error();
    }

    /**
     * 批量删除
     * @param
     * @return
     */
    @PostMapping("/delBatch")
    /**
     * 传一个或者多个Id，批量
     */
    public Object deleteBatch(@RequestBody List<Integer> ids){
        userService.removeByIds(ids);
        return Result.success();
    }

    /**
     *  md5 salt加密的注册
     * @param username
     * @param password
     * @return
     */

    @GetMapping("/login")
    public Result<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {

        // md5(password+salt) = 数据库存的password值
        // 通过用户名查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username)
                .last("limit 1");
        User userInfo = userService.getOne(wrapper);
        HashMap<Object, Object> returnInfo = new HashMap<>();
        if (userInfo != null) {
            if (userInfo.getPassword() != null) {
                if (userInfo.getPassword().equals(SecureUtil.md5(password + userInfo.getSalt()))) {
                    String token = JwtUtils.generateToken(userInfo);
                    HashMap<Object, Object> map = new HashMap<>();
                    map.put("id", userInfo.getId());
                    map.put("username", userInfo.getUsername());
                    map.put("email", userInfo.getEmail());
                    map.put("roleType", userInfo.getRoleType());
                    map.put("token", token);
                    returnInfo.put("userInfo", map);
                    returnInfo.put("menuList", "");
                    return Result.success(map);
                }

            }

        }
        return Result.error("请检查用户名密码是否正确");
    }



    /**
     *  没有进行md5 salt加密的注册
     * @param username
     * @param password
     * @return
     */
/*
    @GetMapping("/login")
    public Result<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username)
                .eq(User::getPassword, password)
                .last("limit 1");
        User userInfo = userService.getOne(wrapper);

        HashMap<Object, Object> returnInfo = new HashMap<>();

        if (userInfo != null) {
            String token = JwtUtils.generateToken(userInfo);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", userInfo.getId());
            map.put("username", userInfo.getUsername());
            map.put("email", userInfo.getEmail());
            map.put("roleType", userInfo.getRoleType());
            map.put("token", token);

            returnInfo.put("userInfo", map);
            returnInfo.put("menuList", "");
            return Result.success(map);
        } else {
            return Result.error("请检查用户名密码是否正确");
        }

    }
*/




}
