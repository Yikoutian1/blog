package com.project.blog.serviceImpl;

import com.project.blog.entity.Article;
import com.project.blog.mapper.ArticleMapper;
import com.project.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Despacito
 * @since 2023-03-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
