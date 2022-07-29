package com.example.basebackend.mapper;

import com.example.basebackend.dto.TestDto;
import com.example.basebackend.entities.TestEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<TestDto, TestEntity> {
}
