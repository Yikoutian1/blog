package com.project.blog.mapper;

import com.project.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author Despacito
 * @since 2023-03-05
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
