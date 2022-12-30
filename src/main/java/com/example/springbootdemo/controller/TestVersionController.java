package com.example.springbootdemo.controller;

import com.example.springbootdemo.annotation.ApiVersion;
import com.example.springbootdemo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/{v}/user")
public class TestVersionController {



    //控制接口的版本,加上注解ApiVersion
    //
    @ApiVersion("1.0.0")
    @GetMapping("/get")
    public User getUserV1(){

        //使用lombok的builder()功能
        return User.builder().uName("awei").uVersion("1.0.0").build();
    }

    @ApiVersion("1.1.0")
    @GetMapping("/get")
    public User getUserV11(){

        //使用lombok的builder()功能
        return User.builder().uName("awei").uVersion("1.1.0").build();
    }

    @ApiVersion("2.0.0")
    @GetMapping("/get")
    public User getUserV2(){

        //使用lombok的builder()功能
        return User.builder().uName("awei").uVersion("2.0.0").build();
    }

    @ApiVersion("3.0.0")
    @GetMapping("/get")
    public User getUserV3(){
        //使用lombok的builder()功能
        return User.builder().uName("awei").uVersion("3.0.0").build();
    }

}
