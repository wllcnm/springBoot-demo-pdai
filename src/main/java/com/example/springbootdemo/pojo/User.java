package com.example.springbootdemo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String uName;

    private String uVersion;

    //使用@JsonFormat注解需要加上timezone属性,否则天数不对
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date creatData;
}
