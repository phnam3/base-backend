package com.example.basebackend.service;

import com.example.basebackend.dto.TestDto;
import com.example.basebackend.entities.TestEntity;
import com.example.basebackend.repository.TestRepository;
import com.example.basebackend.service.core.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl extends BaseServiceImpl<TestEntity, TestDto> implements TestService {

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        super(testRepository);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected TestEntity createEntity(TestDto dto) {
        TestEntity testEntity = new TestEntity();
        copyPojo(dto, testEntity);
        return testEntity;
    }

    @Override
    protected TestEntity createEntity(Long id) {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(id);
        return testEntity;
    }

    @Override
    protected TestDto createDto(TestEntity entity) {
        TestDto testDto = new TestDto();
        copyPojo(entity, testDto);
        return testDto;
    }
}
