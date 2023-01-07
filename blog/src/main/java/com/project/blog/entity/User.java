package com.project.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/1/5 17:08
 * @Version 1.0
 */
@Data
@ApiModel(value = "UserModel",description = "USerModel")
public class User {
    @ApiModelProperty("主键")
    private Integer id;
    private String username;
    private String password;

}
