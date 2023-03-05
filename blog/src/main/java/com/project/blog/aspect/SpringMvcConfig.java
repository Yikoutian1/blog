package com.project.blog.aspect;

import com.project.blog.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName SpringMvcConfig
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/3/5 20:00
 * @Version 1.0
 */

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * 拦截器的注入
     */
    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 所有的请求
                .addPathPatterns("/**")
                // 允许直接访问的接口
                .excludePathPatterns(
                        // 开放的api端口
                        "/user/login",
                        "/article/page",
                        "/article/detail",
                        // swagger,接口
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security"

                );

    }


}