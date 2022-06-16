package com.example.basebackend.service.core;

import com.example.basebackend.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseDto> {
    Long count();

    boolean existsById(Long id);

    T getOne(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();

    List<T> findAllById(List<Long> id);

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable page);

    T save(T dto);

    List<T> saveAll(List<T> dto);

    T update(T dto);

    List<T> updateAll(List<T> listDto);

    void deleteById(Long id);

    void deleteAll(List<Long> ids);

    void deleteAll();
}
