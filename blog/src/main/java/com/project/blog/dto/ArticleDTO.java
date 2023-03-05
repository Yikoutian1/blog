package com.project.blog.dto;

import com.project.blog.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ArticleDTO
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/3/5 20:38
 * @Version 1.0
 */

@Data
@ApiModel(value = "ArticleDTO对象", description = "ArticleDTO对象")
public class ArticleDTO extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("封面图")
    private String picture;

    @ApiModelProperty("所属栏目")
    private Integer categoryId;

    @ApiModelProperty("发布用户")
    private Integer userId;

    @ApiModelProperty("浏览量")
    private Integer views;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("栏目名称")
    private String categoryTitle;

}