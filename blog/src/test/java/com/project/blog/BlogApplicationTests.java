package com.project.blog;

import cn.hutool.crypto.SecureUtil;
import com.project.blog.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BlogApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		String password = "admin";

		log.warn("普通md5加密密码:{}", SecureUtil.md5(password));

		String salt = Utils.salt();
		password = password + salt;

		log.warn("加盐md5加密密码:{}", SecureUtil.md5(password));

	}

}
