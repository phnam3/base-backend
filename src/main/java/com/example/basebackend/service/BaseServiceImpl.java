package com.example.basebackend.service;

import com.example.basebackend.dto.BaseDto;
import com.example.basebackend.entities.BaseEntity;
import com.example.basebackend.exceptions.DataAccessException;
import com.example.basebackend.repository.BaseRepository;
import com.example.basebackend.service.core.BaseService;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseServiceImpl<T extends BaseEntity, dto extends BaseDto> implements BaseService<dto> {

    private final BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    protected abstract Logger getLogger();

    protected abstract T createEntity(dto dto);

    protected abstract T createEntity(Long id);

    protected abstract dto createDto(T entity);

    protected void copyPojo(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    @Override
    public Long count() {
        return baseRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return baseRepository.existsById(id);
    }

    @Override
    public dto getOne(Long id) {
        T entity = baseRepository.getOne(id);
        dto result = createDto(entity);
        return result;
    }

    @Override
    public Optional<dto> findById(Long id) {
        Optional<dto> result = baseRepository.findById(id).map(this::createDto);
        return result;
    }

    @Override
    public List<dto> findAll() {
        List<dto> result = baseRepository.findAll().stream().map(this::createDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<dto> findAll(Sort sort) {
        List<dto> result = baseRepository.findAll(sort).stream().map(this::createDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public Page<dto> findAll(Pageable page) {
        Page<dto> result = baseRepository.findAll(page).map(this::createDto);
        return result;
    }

    @Override
    public List findAllById(List<Long> ids) {
        List<dto> result = baseRepository.findAllById(ids).stream().map(this::createDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public dto save(dto dto) {
        T entity = createEntity(dto);
        entity.setId(null);
        dto result = createDto(baseRepository.save(entity));
        return result;
    }

    @Override
    public List<dto> saveAll(List<dto> dto) {
        List<dto> result = baseRepository.saveAll(StreamSupport.stream(dto.spliterator(), false).map(this::createEntity).collect(Collectors.toList())).stream().map(this::createDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public dto update(dto dto) {
        checkExists(dto.getId());
        T entity = createEntity(dto);
        dto result = createDto(baseRepository.save(entity));
        return result;
    }


    @Override
    public List<dto> updateAll(List<dto> dtos) {
        checkExists(dtos);
        List<dto> result = baseRepository.saveAll(StreamSupport.stream(dtos.spliterator(), false).map(this::createEntity).collect(Collectors.toList())).stream().map(this::createDto).collect(Collectors.toList());
        return result;
    }

    @Override
    public void deleteById(Long id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<Long> ids) {
        baseRepository.deleteAllById(ids);
    }

    private void checkExists(Long id) {
        if (!existsById(id)) {
            String message = String.format("ID: %s not found", id);
            getLogger().error(message);
            throw DataAccessException.notFound(message);
        }
    }

    private void checkExists(dto dto) {
        checkExists(dto.getId());
    }

    private void checkExists(List<dto> dtos) {
        List<String> errors = new ArrayList<>();
        dtos.stream().forEach(dto -> {
            if (!existsById(dto.getId())) {
                errors.add(String.format("ID: %s not found", dto.getId()));
            }
        });
        if (!errors.isEmpty()) {
            throw DataAccessException.notFound("One or more elements are missing", errors);
        }
    }
}
