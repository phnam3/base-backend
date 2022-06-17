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
import org.springframework.data.domain.Sort;
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

    protected abstract Logger getLogger();

    @GetMapping(value = "/count")
    public final ResponseEntity<?> count(){
        Long result = baseService.count();
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/getOne")
    public final ResponseEntity<?> getOne(@RequestParam(value = "id") Long id){
        getLogger().info("Param id: {}", id);
        T result = baseService.getOne(id);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/existsById")
    public final ResponseEntity<?> existsById(Long id){
        getLogger().info("Param id: {}", id);
        boolean result = baseService.existsById(id);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/findById")
    public final ResponseEntity<?> findById(@RequestParam(value = "id") Long id){
        getLogger().info("Param id: {}", id);
        T result = baseService.findById(id).orElseThrow(
                () -> DataAccessException.notFound("Id Not Found")
        );
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/findAll")
    public final ResponseEntity<?> findAll(@RequestParam(value = "page", required=false) Integer page,
                                           @RequestParam(value = "size", required=false) Integer size,
                                           @RequestParam(value = "order", required=false) String order,
                                           @RequestParam(value = "by", required=false) String by){
        Sort sort = createSort(order,by);
        PageRequest pageRequest = createPageRequest(page,size,sort);
        Iterable<T> result;
        if(sort == null && pageRequest == null){
            result = baseService.findAll();
        } else if (pageRequest != null){
            result = baseService.findAll(pageRequest);
        } else {
            result = baseService.findAll(sort);
        }
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/findAllById")
    public final ResponseEntity<?> findAllById(@RequestBody List<Long> ids){
        List<T> result = baseService.findAllById(ids);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/save")
    public final ResponseEntity<?> save(@RequestBody T dto){
        T result = baseService.save(dto);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/saveAll")
    public final ResponseEntity<?> saveAll(@RequestBody List<T> dtos){
        List<T> result = baseService.saveAll(dtos);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/update")
    public final ResponseEntity<?> update(@RequestBody T dto){
        T result = baseService.update(dto);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @PostMapping(value = "/updateAll")
    public final ResponseEntity<?> updateAll(@RequestBody List<T> dtos){
        List<T> result = baseService.updateAll(dtos);
        getLogger().info("Result: {}", result);
        return BaseResponseDto.builder().httpStatus(HttpStatus.OK).data(result).build().toResponseEntity();
    }

    @GetMapping(value = "/deleteById")
    public final ResponseEntity<?> deleteById(Long id){
        getLogger().info("Param id={}", id);
        baseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/deleteAll")
    public final ResponseEntity<?> deleteAll(@RequestBody List<Long> ids){
        getLogger().info("Param id={}", ids);
        baseService.deleteAll(ids);
        return ResponseEntity.noContent().build();
    }

    private Sort createSort(String order, String by){
        getLogger().info("Params: order={}, by={}", order,by);
        Sort sort = null;
        if(order != null && !order.isEmpty() && by != null && !by.isEmpty()){
            if(order.equalsIgnoreCase("ASC")){
                sort = Sort.by(Sort.Direction.ASC, by);
            } else if(order.equalsIgnoreCase("DESC")){
                sort = Sort.by(Sort.Direction.DESC, by);
            }
            getLogger().info("Sort: {}", sort);
        }
        return sort;
    }

    private PageRequest createPageRequest(Integer page, Integer size, Sort sort){
        getLogger().info("Params: page={}, size={}",page,size);
        PageRequest pageRequest = null;
        if(page != null && size != null){
            if(sort != null) {
                pageRequest = PageRequest.of(page, size, sort);
            } else {
                pageRequest = PageRequest.of(page,size);
            }
            getLogger().info("PageRequest: {}", pageRequest);
        }
        return pageRequest;
    }
}
