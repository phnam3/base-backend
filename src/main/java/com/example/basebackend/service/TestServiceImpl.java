package com.example.basebackend.service;

import com.example.basebackend.dto.TestDto;
import com.example.basebackend.entities.TestEntity;
import com.example.basebackend.repository.BaseRepository;
import com.example.basebackend.service.core.TestService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TestServiceImpl extends BaseServiceImpl<TestEntity, TestDto> implements TestService {

    @Autowired
    public TestServiceImpl(BaseRepository<TestEntity> baseRepository) {
        super(baseRepository);
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
