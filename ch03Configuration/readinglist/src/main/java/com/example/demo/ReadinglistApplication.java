package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 开启组件扫描和自动配置
public class ReadinglistApplication{

	public static void main(String[] args) {
	    // 负责启动引导应用程序
		SpringApplication.run(ReadinglistApplication.class, args);
	}
	
}
