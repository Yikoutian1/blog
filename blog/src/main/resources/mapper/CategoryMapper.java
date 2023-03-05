package com.project.blog.mapper;

import com.project.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 栏目表 Mapper 接口
 * </p>
 *
 * @author Despacito
 * @since 2023-03-05
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
