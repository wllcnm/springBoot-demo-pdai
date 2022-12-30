package com.example.springbootdemo.annotation;

import com.example.springbootdemo.校验器.NullOrNotNullValidator;
import com.example.springbootdemo.校验器.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneNumberValidator.class}) // 指定校验器
public @interface PhoneNumber {
    //默认提示的出错信息
    String message() default "格式不正确的手机号码";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
