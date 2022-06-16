package com.example.basebackend.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class BusinessException extends RuntimeException {

    private HttpStatus httpStatus;
    private List<String> errors;

    public BusinessException(String message){
        super(message);
    }

    public static final BusinessException invalidParams(){
        return invalidParams("Not Found");
    }

    public static final BusinessException invalidParams(String message, String...errs){
        return invalidParams(message, Arrays.asList(errs));
    }

    public static final BusinessException invalidParams(String message, List<String> errs) {
        BusinessException businessException = new BusinessException(message);
        businessException.setHttpStatus(HttpStatus.BAD_REQUEST);
        businessException.setErrors(errs);
        return businessException;
    }
}
