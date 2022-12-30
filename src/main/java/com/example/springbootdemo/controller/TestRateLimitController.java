package com.example.springbootdemo.controller;

import com.example.springbootdemo.Response.ResponseResult;
import com.example.springbootdemo.annotation.LogAsp;
import com.taptap.ratelimiter.annotation.RateLimit;
import com.taptap.ratelimiter.annotation.RateLimitKey;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratelimit")
public class TestRateLimitController {


    /*@RateLimit
    有两个最基础的参数，rateInterval 设置了时间窗口，
    rate设置了时间窗口内允许通过的请求数量*/
    @RateLimit(rate = 5, rateInterval = "10s")
    @RequestMapping("/test/get")
    public ResponseResult<String> testRateLimit(@RateLimitKey String name) {
        return ResponseResult.success("请求成功");
    }


}
