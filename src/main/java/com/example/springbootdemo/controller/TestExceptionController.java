package com.example.springbootdemo.controller;

import com.example.springbootdemo.Response.ResponseResult;
import com.example.springbootdemo.pojo.PeopleParam;
import com.example.springbootdemo.pojo.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/exception")
public class TestExceptionController {


    @GetMapping("/add")
    @ResponseBody
    public ResponseResult<PeopleParam> add(@Validated(PeopleParam.AddValidationGroup.class) PeopleParam peopleParam) {
        return ResponseResult.success();
    }


    @GetMapping("/data")
    @ResponseBody
    public User data(Date data) {
        User user = new User();
        user.setCreatData(data);
        return user;
    }
}
