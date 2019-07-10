package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ReadingListServletInitializer extends SpringBootServletInitializer{
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // ReadingListApplication 类上添加了 @SpringBootApplication 注解。
        // 这会隐性开启组件扫描，而组件扫描则会发现并应用其他配置类。
        return builder.sources(ReadingListApplication.class);
    }
}
