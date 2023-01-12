package com.project.blog.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.project.blog.controller.BaseController;
import com.project.blog.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName AutoGenerator
 * @Description TODO
 * @Author QiuLiHang
 * @DATE 2023/1/12 21:31
 * @Version 1.0
 */

public class AutoGenerator {

    /**
     * 本地数据库信息
     */
    private static final String URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1qiulihang";

    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
        tables.add("user");


        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("Qiulihang")               //作者
                            .outputDir(System.getProperty("user.dir") + "\\blog\\src\\main\\java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("com.project.blog")
                            //留空请求路径中就会少一个层级 原层级/blog//user 现层级/user
                            .moduleName("")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, System.getProperty("user.dir") + "\\blog\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
//                            .addTablePrefix("p_")
                            /**
                             * %s ：占位符   xxService
                             */
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .superClass(BaseEntity.class)
                            //开启Lombok，默认生成@Get，@Set，可以手动换成@Data
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.UPDATE))
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            //如果没有父类请注释掉
                            .superClass(BaseController.class)
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();


    }

}