package com.project.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/1/12 21:53
 * @Version 1.0
 */
@Data
@TableName("user")
@ApiModel(value = "User对象", description = "用户信息表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("座右铭")
    @TableField("motto")
    private String motto;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("电话")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("头像")
    @TableField("avatar_url")
    private String avatarUrl;

//    @ApiModelProperty("角色类型")
//    @TableField("role_type")
//    private RoleType roleType;

    @ApiModelProperty("文章总浏览量")
    @TableField("views")
    private Integer views;

    @ApiModelProperty("加盐")
    @TableField("salt")
    private String salt;


}
