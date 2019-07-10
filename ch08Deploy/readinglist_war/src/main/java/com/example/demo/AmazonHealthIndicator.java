package com.example.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AmazonHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            RestTemplate rest = new RestTemplate();
            // 向Amazon发送请求
            rest.getForObject("http://www.amazon.com", String.class);
            return Health.up().withDetail("AmazonHealth Code", "OK").build();
        } catch (Exception e) {
            // 报告 DOWN 状态
            return Health.down().withDetail("AmazonHealth Code", e.getMessage()).build();
        }
    }

}
