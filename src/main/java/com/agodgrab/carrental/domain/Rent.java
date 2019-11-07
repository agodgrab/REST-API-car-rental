package com.kodilla.kodilla.diplomaBackend.domain;

import com.kodilla.kodilla.diplomaBackend.domain.enums.RentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
@NamedNativeQueries({
        @NamedNativeQuery(name= "Rent.updateRentStatus",
                query="UPDATE RENTS R SET R.STATUS= :NEW_STATUS WHERE R.ID = :THIS_ID",
                resultClass = Rent.class),
        @NamedNativeQuery(name="Rent.updateToBePaid",
                query="UPDATE RENTS R SET R.TO_BE_PAID = :PRICE WHERE R.ID = :THIS_ID",
                resultClass = Rent.class)

})
*/
@Entity
@Table(name = "RENTS")
public class Rent {

    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car carRented;

    @Column(name = "START_DAY")
    private LocalDate startDay;

    @Column(name = "END_DAY")
    private LocalDate endDay;

    @Column(name = "FUEL_LEVEL")
    private long fuelLevel;

    @Column(name = "WITH_INSURANCE")
    private boolean withInsurance;

    @Column(name = "WITH_EXTRA_TRUNK")
    private boolean withExtraCarTrunk;

    @OneToMany(
            targetEntity = Penalty.class,
            mappedBy = "rent",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<Penalty> listOfPenalties;

    @Column(name = "TO_BE_PAID")
    private BigDecimal toBePaid;

    @Column(name = "STATUS")
    private RentStatus status;


    public Rent(long id, User user, Car carRented, LocalDate startDay, LocalDate endDay, long fuelLevel, boolean withInsurance, boolean withExtraCarTrunk, List<Penalty> listOfPenalties, BigDecimal toBePaid, RentStatus status) {
        this.id = id;
        this.user = user;
        this.carRented = carRented;
        this.startDay = startDay;
        this.endDay = endDay;
        this.fuelLevel = fuelLevel;
        this.withInsurance = withInsurance;
        this.withExtraCarTrunk = withExtraCarTrunk;
        this.listOfPenalties = listOfPenalties;
        this.toBePaid = toBePaid;
        this.status = status;
    }

    public Rent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCarRented() {
        return carRented;
    }

    public void setCarRented(Car carRented) {
        this.carRented = carRented;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public long getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(long fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public boolean isWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(boolean withInsurance) {
        this.withInsurance = withInsurance;
    }

    public boolean isWithExtraCarTrunk() {
        return withExtraCarTrunk;
    }

    public void setWithExtraCarTrunk(boolean withExtraCarTrunk) {
        this.withExtraCarTrunk = withExtraCarTrunk;
    }

    public List<Penalty> getListOfPenalties() {
        return listOfPenalties;
    }

    public void setListOfPenalties(List<Penalty> listOfPenalties) {
        this.listOfPenalties = listOfPenalties;
    }

    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(BigDecimal toBePaid) {
        this.toBePaid = toBePaid;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }
}
