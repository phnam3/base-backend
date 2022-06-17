package com.example.basebackend.controller.exception_controller;

import com.example.basebackend.dto.BaseResponseDto;
import com.example.basebackend.exceptions.BusinessException;
import com.example.basebackend.exceptions.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity handleDataAccessException(DataAccessException exception){
        return BaseResponseDto.builder()
                .httpStatus(exception.getHttpStatus())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException exception){
        return BaseResponseDto.builder()
                .httpStatus(exception.getHttpStatus())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build()
                .toResponseEntity();
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return BaseResponseDto.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .errors(errors).httpHeaders(headers).build().toResponseEntity();
    }
}
