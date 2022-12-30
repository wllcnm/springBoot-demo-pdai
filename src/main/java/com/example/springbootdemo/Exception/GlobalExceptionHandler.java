package com.example.springbootdemo.Exception;

import cn.hutool.core.date.DateUtil;
import com.example.springbootdemo.Response.ResponseResult;
import com.taptap.ratelimiter.exception.RateLimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pdai
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class, ArithmeticException.class,RateLimitException.class})
    public ExceptionData handleParameterVerificationException(Exception e) {
        ExceptionData.ExceptionDataBuilder exceptionDataBuilder = ExceptionData.builder();
        log.warn("Exception: {}", e.getMessage());
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            bindingResult.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .forEach(exceptionDataBuilder::error);
        } else if (e instanceof ConstraintViolationException) {
            if (e.getMessage() != null) {
                exceptionDataBuilder
                        .error(e.getMessage());
            }
        } else{
            exceptionDataBuilder.timestamp(System.currentTimeMillis());
            exceptionDataBuilder.error(e.getMessage());
            exceptionDataBuilder.message("failed");
            exceptionDataBuilder.status(String.valueOf(HttpStatus.TOO_MANY_REQUESTS.value()));
        }
        return exceptionDataBuilder.build();
    }


    /*
     *
     * 处理参数签名异常
     *
     * */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<BusinessException> processBusinessException(BusinessException businessException) {
        log.error(businessException.getLocalizedMessage());
        return ResponseResult.fail(null, businessException.getLocalizedMessage() == null
                ? com.example.springbootdemo.Response.ResponseStatus.HTTP_STATUS_500.getDescription()
                : businessException.getLocalizedMessage());
    }


//    @ResponseBody
//    @ExceptionHandler(RateLimitException.class)
//    public ResponseResult<BusinessException> processRateLimitException(RateLimitException rateLimitException) {
//        log.error(rateLimitException.getLocalizedMessage());
//        return ResponseResult.fail(null, rateLimitException.getLocalizedMessage() == null
//                ? com.example.springbootdemo.Response.ResponseStatus.HTTP_STATUS_500.getDescription()
//                : rateLimitException.getLocalizedMessage());
//    }



    /*
     *遇到任何异常都会经过该全局异常处理器,然后报服务器内部错误,状态码为500
     *
     * */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult<Exception> processException(Exception exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseResult.fail(null, com.example.springbootdemo.Response.ResponseStatus.HTTP_STATUS_500.getDescription());
    }

    //@InitBinder注解用于自定义参数的解析
    /*
    * @InitBinder注解
      用于请求中注册自定义参数的解析，从而达到自定义请求参数格式的目的；
    * */
    @InitBinder
    public void handleInitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }


}

