package com.project.blog.exception;

import io.swagger.models.auth.In;
import lombok.Getter;

/**
 * @ClassName CustomException
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/3/1 20:14
 * @Version 1.0
 */

/**
 * 自定义异常
 */
@Getter
public class CustomException  extends RuntimeException{
    private Integer code;
    public CustomException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
    public CustomException(String msg) {
        super(msg);
    }

}
