package com.example.basebackend.service;

import com.example.basebackend.dto.TestDto;
import com.example.basebackend.entities.TestEntity;
import com.example.basebackend.mapper.TestMapper;
import com.example.basebackend.repository.TestRepository;
import com.example.basebackend.service.core.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestServiceImpl extends BaseServiceImpl<TestEntity, TestDto> implements TestService {

    private final TestMapper testMapper;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, TestMapper testMapper) {
        super(testRepository);
        this.testMapper = testMapper;
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TestEntity createEntity(TestDto dto) {
        return testMapper.toEntity(dto);
    }

    @Override
    protected List<TestEntity> createEntityList(List<TestDto> dtos) {
        return testMapper.toEntityList(dtos);
    }

    @Override
    protected TestEntity createEntity(Long id) {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(id);
        return testEntity;
    }

    @Override
    protected TestDto createDto(TestEntity entity) {
        return testMapper.toDto(entity);
    }
    
    @Override
    protected List<TestDto> createDtoList(List<TestEntity> entities) {
        return testMapper.toDtoList(entities);
    }

}
