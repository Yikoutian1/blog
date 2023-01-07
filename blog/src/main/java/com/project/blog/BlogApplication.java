package com.project.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author Qiulihang
 */
@Configuration
@MapperScan({"com.project.blog.*.mapper","org.project.blog.*.mapper"})
@SpringBootApplication
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
