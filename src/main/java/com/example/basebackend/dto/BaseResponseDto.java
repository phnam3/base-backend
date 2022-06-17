package com.example.basebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BaseResponseDto<T> {
    @JsonIgnore
    private HttpStatus httpStatus;
    @JsonIgnore
    private HttpHeaders httpHeaders;
    private Integer code;
    private T data;
    private String message;
    private List<String> errors;

    @PostConstruct
    private void init(){
        httpStatus = HttpStatus.OK;
        code = httpStatus.value();
    }

    public ResponseEntity<BaseResponseDto> toResponseEntity() {
        return new ResponseEntity<>(this,httpHeaders,httpStatus);
    }
}
