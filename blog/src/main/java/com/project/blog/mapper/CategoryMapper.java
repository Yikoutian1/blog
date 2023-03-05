package com.project.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.blog.entity.Category;
import com.project.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/1/5 17:10
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
