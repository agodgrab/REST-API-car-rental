package com.kodilla.kodilla.diplomaBackend.Car.domain;

import com.kodilla.kodilla.diplomaBackend.Category.domain.Category;
import com.kodilla.kodilla.diplomaBackend.Rent.domain.Rent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name="Car.searchCarInDateRange",
        query = "SELECT * FROM CARS C WHERE " +
                "ID NOT IN " +
                    "(SELECT R.CAR_ID FROM RENTS R WHERE " +
                        "(R.START_DAY BETWEEN :REQUESTED_START AND :REQUESTED_END) OR " +
                        "(R.END_DAY BETWEEN :REQUESTED_START AND :REQUESTED_END) OR " +
                        "(R.START_DAY < :REQUESTED_START AND R.END_DAY > :REQUESTED_END ))" +
                "AND C.CATEGORY_ID = :REQUESTED_CATEGORY",
        resultClass = Car.class
)
@Entity
@Table(name="CARS")
public class Car {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    @Id
    private long id;

    @Column(name="MODEL")
    private String model;

    @Column(name="PRODUCTION_YEAR")
    private String productionYear;

    @Column(name="VEHICLE_MILEAGE")
    private BigDecimal vehicleMileage;

    @Column(name="DOOR_QTY")
    private int doorQuantity;

    @Column(name="SEATS_QTY")
    private int seatsQuantity;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "carRented",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    List<Rent> listOfRents=new ArrayList<>();

    public Car(long id, String model, String productionYear, BigDecimal vehicleMileage, int doorQuantity, int seatsQuantity, Category category, List<Rent> listOfRents) {
        this.id=id;
        this.model = model;
        this.productionYear = productionYear;
        this.vehicleMileage = vehicleMileage;
        this.doorQuantity = doorQuantity;
        this.seatsQuantity = seatsQuantity;
        this.category = category;
        this.listOfRents = listOfRents;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public BigDecimal getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(BigDecimal vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    public int getDoorQuantity() {
        return doorQuantity;
    }

    public void setDoorQuantity(int doorQuantity) {
        this.doorQuantity = doorQuantity;
    }

    public int getSeatsQuantity() {
        return seatsQuantity;
    }

    public void setSeatsQuantity(int seatsQuantity) {
        this.seatsQuantity = seatsQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Rent> getListOfRents() {
        return listOfRents;
    }

    public void setListOfRents(List<Rent> listOfRents) {
        this.listOfRents = listOfRents;
    }
}
