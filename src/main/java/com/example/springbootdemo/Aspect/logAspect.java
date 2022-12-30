package com.example.springbootdemo.Aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class logAspect {


    @Pointcut("execution(@com.example.springbootdemo.annotation.LogAsp * * (..))")
    public void poincut(){

    }

    /*在切入点之前执行*/
    @Before("poincut()")
    public void logasp(){
        System.out.println("在方法执行前");
    }

    @Pointcut("execution(* com.example.springbootdemo.controller.TestRateLimitController.*(..))")
    public  void poincut1(){}


    @Before("poincut1()")
    public void logasp01(){
        System.out.println("在方法执行前");

    }
}
