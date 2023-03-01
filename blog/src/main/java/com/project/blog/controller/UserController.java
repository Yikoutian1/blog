package com.project.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.UserQuery;
import com.project.blog.entity.User;
import io.micrometer.common.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    /**
     * list
     * @return
     */
    @GetMapping("/list")
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
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        /**
         * 通过ID降序排列
         */
        // 特性: jdk8中使用了::的用法。就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的“方法引用”
        userLambdaQueryWrapper.orderByDesc(User::getId);

        // 如果满足则进行名字的模糊匹配
        // StringUtils.isBlank：判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
        if(!StringUtils.isBlank(userQuery.getUsername())){
            // 模糊匹配我们的Username
            userLambdaQueryWrapper.like(User::getUsername,userQuery.getUsername());
        }

        // 为什么要分页，因为如果数据多了，那么不可能一次性全部查询的
        //page(xx,yy)  xx:分页对象 yy:查询条件
        Page<User> page = userService.page(new Page<>(
                // 分页信息
                        userQuery.getPageNum(),
                        userQuery.getPageSize()),
                // 分页条件
                userLambdaQueryWrapper
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
    public Object save(@Validated @RequestBody User user){
        userService.saveOrUpdate(user);
        return Result.success();
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
}
