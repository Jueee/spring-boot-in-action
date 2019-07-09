package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;


@Component
public class ApplicationContextMetrics implements MeterBinder{

    @Autowired
    private ApplicationContext context;
    
    @Override
    public void bindTo(MeterRegistry registry) {
        Counter.builder("spring.context.startup-date")
            .tags(new String[]{"StartupDate", String.valueOf(context.getStartupDate())})
            .description("记录启动时间")
            .register(registry);
        Counter.builder("spring.beans.definitions")
            .tags(new String[]{"BeanDefinitionCount", String.valueOf(context.getBeanDefinitionCount())})
            .description("记录Bean定义数量")
            .register(registry);
        Counter.builder("spring.beans")
            .tags(new String[]{"beansNum", String.valueOf(context.getBeanNamesForType(Object.class).length)})
            .description("记录Bean数量")
            .register(registry);
        Counter.builder("spring.controllers")
            .tags(new String[]{"controllersNum", String.valueOf(context.getBeanNamesForAnnotation(Controller.class).length)})
            .description("记录控制器类型的Bean数量")
            .register(registry);
    }

}



