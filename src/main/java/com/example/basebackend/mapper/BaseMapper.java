package com.example.basebackend.mapper;

import com.example.basebackend.dto.BaseDto;
import com.example.basebackend.entities.BaseEntity;

public interface BaseMapper<dto extends BaseDto, T extends BaseEntity> extends GenericMapper<dto, T> {
}
