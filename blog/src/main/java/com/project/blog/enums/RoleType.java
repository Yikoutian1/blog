package com.project.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @ClassName RoleType
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/3/5 19:24
 * @Version 1.0
 */

@Getter
public enum RoleType {
    ROLE_ADMIN( "ROLE_ADMIN"),
    ROLE_USER( "ROLE_USER");

    @EnumValue
    private String name;


    RoleType(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
