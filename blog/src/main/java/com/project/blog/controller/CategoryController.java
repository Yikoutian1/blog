package com.project.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.PageInfo;
import com.project.blog.dto.UserQuery;
import com.project.blog.entity.Category;
import com.project.blog.utils.JwtUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.project.blog.controller.BaseController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 栏目表 前端控制器
 * </p>
 *
 * @author Despacito
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    /**
     * list
     * @return
     */
    @GetMapping("/list")
    public List<Category> list(){
        return categoryService.list();
    }

    /**
     * page
     * @param pageInfo
     * @return
     */
    @PostMapping("/page")
    public Result<?> findPage(@RequestBody PageInfo pageInfo){
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        /**
         * 通过ID降序排列
         */
        // 特性: jdk8中使用了::的用法。就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的“方法引用”
        wrapper.orderByDesc(Category::getId);


        // 为什么要分页，因为如果数据多了，那么不可能一次性全部查询的
        //page(xx,yy)  xx:分页对象 yy:查询条件
        Page<Category> page = categoryService.page(new Page<>(
                        // 分页信息
                        pageInfo.getPageNum(),
                        pageInfo.getPageSize()),
                // 分页条件
                wrapper
        );
        // 返回结果
        return Result.success(page);
    }

    /**
     * save
     * @param category
     * @return
     */
    @PostMapping("/save")
    public Object save(@Validated @RequestBody Category category){
        categoryService.saveOrUpdate(category);
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
        categoryService.removeByIds(ids);
        return Result.success();
    }

}
