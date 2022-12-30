package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.PeopleParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/people")
public class TestValidationController {


    @GetMapping("/update")
    @ResponseBody
    public Object update(@Validated(PeopleParam.EditValidationGroup.class) PeopleParam peopleParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<ObjectError> allErrors = bindingResult.getAllErrors();

            allErrors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                //占位符输出日志
                log.error("错误的参数:object-{},field-{},errorMessage-{}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return allErrors;
        }
        return "参数无误";
    }

    @GetMapping("/add")
    @ResponseBody
    public Object add(@Validated(PeopleParam.AddValidationGroup.class) PeopleParam peopleParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<ObjectError> allErrors = bindingResult.getAllErrors();

            allErrors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                //占位符输出日志
                log.error("错误的参数:object-{},field-{},errorMessage-{}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return allErrors;
        }
        return "参数无误";
    }




}
