package com.project.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.ArticleDTO;
import com.project.blog.dto.PageInfo;
import com.project.blog.dto.UserQuery;
import com.project.blog.entity.Article;
import com.project.blog.entity.Category;
import com.project.blog.entity.User;
import io.micrometer.common.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.project.blog.controller.BaseController;

import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Despacito
 * @since 2023-03-05
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
    /**
     * list
     * @return
     */
    @GetMapping("/list")
    public List<Article> list(){
        return articleService.list();
    }

    /**
     * page
     * @param
     * @return
     */
    @PostMapping("/page")
    public Result<?> findPage(@RequestBody PageInfo pageInfo){
    LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();


    // 为什么要分页，因为如果数据多了，那么不可能一次性全部查询的
    //page(xx,yy)  xx:分页对象 yy:查询条件
    Page<Article> page = articleService.page(new Page<>(
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
     * @param article
     * @return
     */
    @PostMapping("/save")
    public Object save(@Validated @RequestBody Article article){
        articleService.saveOrUpdate(article);
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
        articleService.removeByIds(ids);
        return Result.success();
    }
    @GetMapping("/detail")
    public Result<?> detail(@RequestParam("id")Integer id/*每一条数据的主键*/){
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getId,id)
                .last("limit 1");
        Article article = articleService.getOne(wrapper);
        if(BeanUtil.isEmpty(article)){
            return Result.error("没有找到该数据");
        }else{
            // 浏览量+1
            if(article.getViews() == null){// 没有浏览过
                article.setViews(0);
            }else {
                article.setViews(article.getViews() + 1);
            }
            ArticleDTO articleDTO = new ArticleDTO();
            // 赋值给右边
            BeanUtil.copyProperties(article,articleDTO);
            // 查询出发布人信息
            if(article.getUserId()!=null){
                User userInfo = userService.getById(article.getUserId());
                if (userInfo!=null){
                    articleDTO.setUsername(userInfo.getUsername());
                }
            }
            // 查出文章栏目信息
            if (article.getUserId() != null) {
                Category categoryInfo = categoryService.getById(article.getCategoryId());
                if (categoryInfo != null) {
                    articleDTO.setUsername(categoryInfo.getTitle());
                }
            }
            // 浏览量+1,通过id进行更新,因为在上面已经完成了+1操作
            articleService.updateById(article);
            // 更新完成后,返回前端
            return Result.success(articleDTO);
        }
    }
}
