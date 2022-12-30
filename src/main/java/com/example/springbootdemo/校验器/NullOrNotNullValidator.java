package com.example.springbootdemo.校验器;

import com.example.springbootdemo.annotation.NullOrNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/*
* 指定注解校验器
* 需要该校验器继承ConstraintValidator<>,重写isValid方法,
* 传入的参数为注解接受的参数,然后对该参数进行校验,如果符合规则则返回ture,否则抛出异常,返回false
*
* */
public class NullOrNotNullValidator implements ConstraintValidator<NullOrNotNull,Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (Integer.parseInt(String.valueOf(o))>=10){
                throw new RuntimeException("数值需要小于10");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
