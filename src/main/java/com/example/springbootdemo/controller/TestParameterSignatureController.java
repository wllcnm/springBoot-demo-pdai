package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.Response.ResponseResult;
import com.example.springbootdemo.annotation.Signature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/signature")
public class TestParameterSignatureController {


    @Signature
    @GetMapping("/test/params")
    public ResponseResult<Object> testParams(String client, Integer userId){
        log.info(client+""+userId);
        Map<String,Object> map=new HashMap<>();
        map.put("client",client);
        map.put("userId",userId);
        return ResponseResult.success(map);
    }
}
