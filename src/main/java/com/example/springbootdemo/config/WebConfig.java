package com.example.springbootdemo.config;

import com.example.springbootdemo.intercept.RedisUrlCountIntercept;
import com.example.springbootdemo.version.ApiVersionRequestMappingHandlerMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {




    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        //版本控制的HandlerMapping
        return new ApiVersionRequestMappingHandlerMapping();
    }


}
