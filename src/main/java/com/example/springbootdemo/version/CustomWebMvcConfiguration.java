package com.example.springbootdemo.version;

import com.example.springbootdemo.intercept.RedisUrlCountIntercept;
import com.example.springbootdemo.version.ApiVersionRequestMappingHandlerMapping;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;

/*
* 如果有WebMvcConfigurationSupport,则WebMvcConfiguration会失效
* */
@Configuration
public class CustomWebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    RedisUrlCountIntercept redisUrlCountIntercept;


    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redisUrlCountIntercept)
                .addPathPatterns("/**");
    }


    /**
     * 解决同时集成spring-boot-starter-actuator、springfox-boot-starter报错问题
     * @return
     */
}

