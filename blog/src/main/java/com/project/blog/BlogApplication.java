package com.project.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Qiulihang
 */
@Configuration
@MapperScan({"com.project.blog.*.mapper","org.project.blog.*.mapper"})
@SpringBootApplication
@CrossOrigin
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
