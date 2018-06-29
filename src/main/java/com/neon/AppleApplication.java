package com.neon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 开启事物注解
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.neon.*" })
@MapperScan("com.neon.mapper")
public class AppleApplication {

	@RequestMapping("/")
	String home() {
		return "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppleApplication.class, args);
		System.out.println("-----------------------启动成功----------------------------");
	}

}
