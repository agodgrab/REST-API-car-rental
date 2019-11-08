package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.Category;
import com.agodgrab.carrental.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<CategoryDto, Category> {

    @Autowired
    CarMapper carMapper;

    @Override
    public CategoryDto mapToDto(Category category) {
        return new CategoryDto.CategoryDtoBuilder()
                .id(category.getId())
                .name(category.getName())
                .details(category.getDetails())
                .pricePerDay(category.getPricePerDay())
                .listOfCarsDto(carMapper.mapToDtoList(category.getListOfCars()))
                .build();
    }

    @Override
    public Category mapToEntity(CategoryDto categoryDto) {
        return new Category(categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getDetails(),
                categoryDto.getPricePerDay(),
                carMapper.mapToEntitiesList(categoryDto.getListOfCarsDto()));
    }
}
