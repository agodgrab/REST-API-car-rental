package com.kodilla.kodilla.diplomaBackend.Penalty.domain;

import com.kodilla.kodilla.diplomaBackend.Rent.domain.Rent;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="PENALTIES")
public class Penalty {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    @Id
    private long id;

    @Column
    private PenaltyReason reason;

    @Column
    private String details;

    @Column
    private BigDecimal toBePaid;

    @ManyToOne
    @JoinColumn(name="RENT_ID")
    private Rent rent;

    public Penalty(long id, PenaltyReason reason, String details, BigDecimal toBePaid, Rent rent) {
        this.id=id;
        this.reason = reason;
        this.details = details;
        this.toBePaid = toBePaid;
        this.rent = rent;
    }

    public Penalty() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PenaltyReason getReason() {
        return reason;
    }

    public void setReason(PenaltyReason reason) {
        this.reason = reason;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(BigDecimal toBePaid) {
        this.toBePaid = toBePaid;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
