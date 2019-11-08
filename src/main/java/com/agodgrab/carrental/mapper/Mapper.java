package com.agodgrab.carrental.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<D, E> {

    D mapToDto(E entity);

    E mapToEntity(D dto);

    default List<D> mapToDtoList(List<E> entities){
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    default List<E> mapToEntitiesList(List<D> dtos){
        return dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

}
