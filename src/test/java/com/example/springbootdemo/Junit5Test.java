package com.example.springbootdemo;

import com.example.springbootdemo.annotation.LogAsp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

@DisplayName("Junit测试")
public class Junit5Test {

    @ParameterizedTest
    @DisplayName("参数化测试")
    @ValueSource(ints={1,2,3,4,5})
    void testParameterized(int i){
        System.out.println(i);
    }

    @ParameterizedTest
    @DisplayName("参数化测试2")
    @MethodSource("stringStream")
    void testParameterized2(String i){
        System.out.println(i);
    }


    static Stream<String> stringStream(){
        return Stream.of("cxk","jojo","gugu");
    }

    @LogAsp
    @DisplayName("异常断言")
    @Test
    void testException(){
        //断定业务逻辑一定出现错误
        Assertions.assertThrows(ArithmeticException.class,()->{
           int i=10/0;
        },"业务逻辑居然正常运行?");
    }

    @DisplayName("快速失败")
    @Test
    void testFail(){
        if (1==2){
            Assertions.fail("测试失败");
        }
    }

}
