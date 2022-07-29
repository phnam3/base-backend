package com.example.basebackend.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericMapper<Dto, Entity> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);
    List<Dto> toDtoList(List<Entity> entities);
    List<Entity> toEntityList(List<Dto> dtos);

}
