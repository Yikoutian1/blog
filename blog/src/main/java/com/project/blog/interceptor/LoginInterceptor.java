package com.project.blog.interceptor;

import cn.hutool.jwt.Claims;
import com.project.blog.entity.User;
import com.project.blog.enums.RoleType;
import com.project.blog.exception.CustomException;
import com.project.blog.serviceImpl.UserServiceImpl;
import com.project.blog.utils.JwtUtils;
import com.project.blog.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印请求信息
        log.info("------------- LoginInterceptor 开始 -------------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURL().toString();
        log.info("接口登录拦截：，path：{}", path);

        //获取header的token参数
        String token = request.getHeader("token");
        log.info("登录校验开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            log.info( "token为空，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }


        Claims claims = JwtUtils.verifyJwt(token);
        //获取用户ID
        if (claims == null) {
            log.warn( "token无效，请求被拦截" );
            throw new CustomException(401,"token无效，请求被拦截");
        } else {

            Boolean admin = Utils.isAdmin(path);
            if(admin){
                //判断当前访问的用户是否是超级管理员权限
                Integer userId = (Integer)claims.getClaim("userId");
                log.info("已登录用户ID：{}", userId);
                User userInfo = userService.getById(userId);
                if(userInfo != null){
                    if (!RoleType.ROLE_ADMIN.equals(userInfo.getRoleType())) {
                        log.warn( "接口无权限访问" );
                        throw new CustomException(401,"接口无权限访问");
                    }
                }else {
                    log.warn( "用户未找到" );
                    throw new CustomException(401,"用户未找到");
                }
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("requestStartTime");
        log.info("------------- LoginInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("LogInterceptor 结束");
    }
}
