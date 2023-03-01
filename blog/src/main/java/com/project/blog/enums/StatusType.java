package com.project.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @ClassName StatusType
 * @Description 枚举类型
 * @Author QiuLiHang
 * @DATE 2023/3/1 20:08
 * @Version 1.0
 */

@Getter
public enum StatusType {
    NO(0, "禁用"),
    YES(1, "启用");
    /**
     * 数据库属性
     */
    @EnumValue
    private Integer key;
    /**
     * 前端显示
     */
    @JsonValue
    private String name;

    StatusType(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}