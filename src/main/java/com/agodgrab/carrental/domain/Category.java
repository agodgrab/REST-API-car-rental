package com.agodgrab.carrental.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    @Id
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "PRICE_PER_DAY")
    private BigDecimal pricePerDay;

    @OneToMany(
            targetEntity = Car.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER,
            mappedBy = "category")
    private List<Car> listOfCars = new ArrayList<>();

    public Category(long id, String name, String details, BigDecimal pricePerDay, List<Car> listOfCars) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.pricePerDay = pricePerDay;
        this.listOfCars = listOfCars;
    }

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public List<Car> getListOfCars() {
        return listOfCars;
    }

    public void setListOfCars(List<Car> listOfCars) {
        this.listOfCars = listOfCars;
    }
}
