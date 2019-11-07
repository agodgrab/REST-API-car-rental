package com.kodilla.kodilla.diplomaBackend.dto;

import java.math.BigDecimal;
import java.util.List;

public class CategoryDto {

    private long id;
    private String name;
    private String details;
    private BigDecimal pricePerDay;
    private List<CarDto> listOfCarsDto;

    public static class CategoryDtoBuilder {
        private long id;
        private String name;
        private String details;
        private BigDecimal pricePerDay;
        private List<CarDto> listOfCarsDto;

        public CategoryDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CategoryDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryDtoBuilder details(String details) {
            this.details = details;
            return this;
        }

        public CategoryDtoBuilder pricePerDay(BigDecimal pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }

        public CategoryDtoBuilder listOfCarsDto(List<CarDto> listOfCarsDto) {
            this.listOfCarsDto = listOfCarsDto;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(id, name, details, pricePerDay, listOfCarsDto);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public List<CarDto> getListOfCarsDto() {
        return listOfCarsDto;
    }

    public CategoryDto(long id, String name, String details, BigDecimal pricePerDay, List<CarDto> listOfCarsDto) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.pricePerDay = pricePerDay;
        this.listOfCarsDto = listOfCarsDto;
    }

    public CategoryDto() {
    }
}
