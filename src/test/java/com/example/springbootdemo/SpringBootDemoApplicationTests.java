package com.example.springbootdemo;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootDemoApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;




    @Disabled
    @Timeout(value = 10,unit = TimeUnit.SECONDS)
    @DisplayName("测试restTemplate获取外部接口")
    @RepeatedTest(5)
    @Test
    void contextLoads() {

        String url="https://api.vvhan.com/api/wbhot";

        //使用restTemplate获取外部接口
        RestTemplate restTemplate=new RestTemplate();
        //params为传入的参数
        Map<String,String> params=new HashMap<>();
        //params.put("name","dada");  //
        //使用.getForObject获取结果,封装类型为你传入的参数类型
        String jsonObject = restTemplate.getForObject(url, String.class);
        //使用.getForEntity获取结果,封装类型为为ResponseEntity<T>,T为你传入的参数类型
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        //使用fastjson将String转化为JSON格式
        Object json1 = JSON.parse(jsonObject);
       
        System.out.println(json1);
        System.out.println(forEntity);
    }


    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        operations.set("hello","world");
        operations.set("123","456",120, TimeUnit.SECONDS);

    }

}
