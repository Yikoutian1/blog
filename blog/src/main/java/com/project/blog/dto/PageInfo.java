package com.project.blog.dto;

import lombok.Data;

/**
 * @ClassName PageInfo
 * @Description 分页
 * @Author QiuLiHang
 * @DATE 2023/1/13 22:36
 * @Version 1.0
 */
@Data
public class PageInfo {
    /**
     * 分页信息
     */
    private Integer pageNum;
    private Integer pageSize;
}
