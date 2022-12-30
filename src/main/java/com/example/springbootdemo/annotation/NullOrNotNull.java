package com.example.springbootdemo.annotation;

import com.example.springbootdemo.校验器.NullOrNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NullOrNotNullValidator.class}) // 指定校验器
public @interface NullOrNotNull {
    String message() default "Invalid telephone number";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

