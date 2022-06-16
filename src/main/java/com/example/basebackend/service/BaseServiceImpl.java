package com.example.basebackend.service;

import com.example.basebackend.dto.BaseDto;
import com.example.basebackend.entities.BaseEntity;
import com.example.basebackend.exceptions.DataAccessException;
import com.example.basebackend.repository.BaseRepository;
import com.example.basebackend.service.core.BaseService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends BaseEntity, dto extends BaseDto> implements BaseService<dto> {

    private final BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    protected abstract Logger getLogger();

    protected abstract T createEntity(dto dto);

    protected abstract T createEntity(Long id);

    protected abstract dto createDto(T entity);

    protected void copyPojo(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }

    @Override
    public Long count() {
        Long count = baseRepository.count();
        return count;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public dto getOne(Long id) {
        return null;
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List findAll(Sort sort) {
        return null;
    }

    @Override
    public Page findAll(Pageable page) {
        return null;
    }

    @Override
    public dto save(dto dto) {
        T entity = createEntity(dto);
        entity.setId(null);
        baseRepository.save(entity);
        return dto;
    }

    @Override
    public dto update(dto dto) {

        return null;
    }

    @Override
    public List saveAll(List dto) {
        return null;
    }


    @Override
    public List updateAll(List listDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteAll(List ids) {

    }

    @Override
    public List findAllById(List id) {
        return null;
    }

    private void checkExists(Long id){
        if(!existsById(id)){
            String message = String.format("ID: %s not found", id);
            getLogger().error(message);
            throw DataAccessException.notFound(message);
        }
    }

    private void checkExists(dto dto){
        checkExists(dto.getId());
    }

    private void checkExists(List<dto> dtos){
        List<String> errors = new ArrayList<>();
        dtos.stream().forEach(dto -> {
            if (!existsById(dto.getId())){
                errors.add(String.format("ID: %s not found", dto.getId()));
            }
        });
        if(!errors.isEmpty()){
            throw DataAccessException.notFound("One or more elements are missing", errors);
        }
    }
}
