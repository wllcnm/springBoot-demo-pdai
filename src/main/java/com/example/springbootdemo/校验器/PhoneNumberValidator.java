package com.example.springbootdemo.校验器;

import com.example.springbootdemo.annotation.PhoneNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    public static final String REGEX_phone ="^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return Pattern.matches(REGEX_phone, phoneNumber);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }
}
