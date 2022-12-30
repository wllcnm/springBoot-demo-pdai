package com.example.springbootdemo.Exception;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

/**
 * This class is for xxxx.
 *
 * @author pdai
 */
@Data
@Builder
public class ExceptionData {

    @Singular
    private final List<Object> errors;

    private String message;

    private String status;

    private long timestamp;

}
