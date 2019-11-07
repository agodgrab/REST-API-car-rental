package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.Category;
import com.agodgrab.carrental.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    CarMapper carMapper;

    public Category mapToCategory(CategoryDto categoryDto) {
        return new Category(categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getDetails(),
                categoryDto.getPricePerDay(),
                carMapper.mapToCarList(categoryDto.getListOfCarsDto()));
    }

    public CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto.CategoryDtoBuilder()
                .id(category.getId())
                .name(category.getName())
                .details(category.getDetails())
                .pricePerDay(category.getPricePerDay())
                .listOfCarsDto(carMapper.mapToCarDtoList(category.getListOfCars()))
                .build();
    }
}
