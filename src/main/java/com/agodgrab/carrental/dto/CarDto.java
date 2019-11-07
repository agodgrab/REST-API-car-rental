package com.agodgrab.carrental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

public class CarDto {

    private long id;
    private String model;
    private String productionYear;
    private BigDecimal vehicleMileage;
    private int doorQuantity;
    private int seatsQuantity;
    private long categoryId;
    @JsonIgnore
    List<RentDto> listOfRents;

    public static class CarDtoBuilder {

        private long id;
        private String model;
        private String productionYear;
        private BigDecimal vehicleMileage;
        private int doorQuantity;
        private int seatsQuantity;
        private long categoryId;
        List<RentDto> listOfRents;

        public CarDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CarDtoBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarDtoBuilder productionYear(String productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public CarDtoBuilder vehicleMileage(BigDecimal vehicleMileage) {
            this.vehicleMileage = vehicleMileage;
            return this;
        }

        public CarDtoBuilder doorQuantity(int doorQuantity) {
            this.doorQuantity = doorQuantity;
            return this;
        }

        public CarDtoBuilder seatsQuantity(int seatsQuantity) {
            this.seatsQuantity = seatsQuantity;
            return this;
        }

        public CarDtoBuilder categoryId(long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public CarDtoBuilder listOfRents(List<RentDto> listOfRents) {
            this.listOfRents = listOfRents;
            return this;
        }

        public CarDto build() {
            return new CarDto(id, model, productionYear, vehicleMileage, doorQuantity, seatsQuantity, categoryId, listOfRents);
        }
    }

    private CarDto(long id, String model, String productionYear, BigDecimal vehicleMileage, int doorQuantity, int seatsQuantity, long categoryId, List<RentDto> listOfRents) {
        this.id = id;
        this.model = model;
        this.productionYear = productionYear;
        this.vehicleMileage = vehicleMileage;
        this.doorQuantity = doorQuantity;
        this.seatsQuantity = seatsQuantity;
        this.categoryId = categoryId;
        this.listOfRents = listOfRents;
    }

    public CarDto() {
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public BigDecimal getVehicleMileage() {
        return vehicleMileage;
    }

    public int getDoorQuantity() {
        return doorQuantity;
    }

    public int getSeatsQuantity() {
        return seatsQuantity;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public List<RentDto> getListOfRents() {
        return listOfRents;
    }
}
