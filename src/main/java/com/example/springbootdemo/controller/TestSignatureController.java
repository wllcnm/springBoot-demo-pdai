package com.example.springbootdemo.controller;

import com.example.springbootdemo.Response.ResponseResult;
import com.example.springbootdemo.annotation.Signature;
import com.example.springbootdemo.pojo.PeopleParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSignatureController {


    @Signature
    @GetMapping("/signature")
    public ResponseResult<String> testSignature(PeopleParam peopleParam) {
        return ResponseResult.success("请求成功");
    }


}
