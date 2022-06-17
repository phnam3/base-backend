package com.example.basebackend.service.core;

import com.example.basebackend.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BaseService<dto extends BaseDto> {
    Long count();

    boolean existsById(Long id);

    dto getOne(Long id);

    Optional<dto> findById(Long id);

    List<dto> findAll();

    List<dto> findAllById(List<Long> id);

    List<dto> findAll(Sort sort);

    Page<dto> findAll(Pageable page);

    dto save(dto dto);

    List<dto> saveAll(List<dto> dto);

    dto update(dto dto);

    List<dto> updateAll(List<dto> listDto);

    void deleteById(Long id);

    void deleteAll(List<Long> ids);

    void deleteAll();
}
