package com.example.basebackend.controller.rest_controller;

import com.example.basebackend.dto.BaseDto;
import com.example.basebackend.dto.BaseResponseDto;
import com.example.basebackend.dto.TestDto;
import com.example.basebackend.exceptions.DataAccessException;
import com.example.basebackend.service.core.BaseService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<T extends BaseDto> {

    private final BaseService<T> baseService;

    protected BaseController(BaseService<T> baseService) {
        this.baseService = baseService;
    }


    @GetMapping(value = "/count")
    public final ResponseEntity<?> count(){
        Long result = baseService.count();
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/getOne")
    public final ResponseEntity<?> getOne(@RequestParam(value = "id") Long id){
        T result = baseService.getOne(id);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/existsById")
    public final ResponseEntity<?> existsById(Long id){
        boolean result = baseService.existsById(id);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/findById")
    public final ResponseEntity<?> findById(@RequestParam(value = "id") Long id){
        T result = baseService.findById(id).orElseThrow(
                () -> DataAccessException.notFound("Id Not Found")
        );
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/findAll")
    public final ResponseEntity<?> findAll(@PageableDefault Pageable pageable){
        Iterable<T> result = baseService.findAll(pageable);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/findAllById")
    public final ResponseEntity<?> findAllById(@RequestBody List<Long> ids){
        List<T> result = baseService.findAllById(ids);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/save")
    public final ResponseEntity<?> save(@RequestBody T dto){
        T result = baseService.save(dto);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/saveAll")
    public final ResponseEntity<?> saveAll(@RequestBody List<T> dtos){
        List<T> result = baseService.saveAll(dtos);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/update")
    public final ResponseEntity<?> update(@RequestBody T dto){
        T result = baseService.update(dto);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/updateAll")
    public final ResponseEntity<?> updateAll(@RequestBody List<T> dtos){
        List<T> result = baseService.updateAll(dtos);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/deleteById")
    public final ResponseEntity<?> deleteById(Long id){
        baseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteAll")
    public final ResponseEntity<?> deleteAll(@RequestBody List<Long> ids){
        baseService.deleteAll(ids);
        return ResponseEntity.noContent().build();
    }
}
