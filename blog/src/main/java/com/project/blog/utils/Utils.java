package com.project.blog.utils;

import cn.hutool.crypto.SecureUtil;

import java.util.Random;

public class Utils {
    //定义超级管理员可以访问的接口
    private final static String[] adminUrlList = {
            "/article/delBatch",
            "/article/save",

            "/category/",
            "/category/delBatch",
            "/category/page",
            "/category/save",

            "/user/delBatch",
            "/user/page",
            "/user/",
            "/user/save",

    };



    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 自定义简单生成盐，是一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = /*salt()*/"1629ED5FE78967BD";
        System.out.println(salt);
        //2e400f7fe59147f895338b4ee9011239
        System.out.println(SecureUtil.md5("getToken" + salt));
    }

    public static Boolean isAdmin(String url){
        boolean isFlag = false;
        for (String s:adminUrlList) {
            if (url.contains(s)) {
                isFlag = true;
            }
        }
        return  isFlag;
    }

}
