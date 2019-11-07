package com.agodgrab.carrental.dto;

import com.agodgrab.carrental.domain.enums.PenaltyReason;

import java.math.BigDecimal;

public class PenaltyDto {

    private long id;
    private PenaltyReason reason;
    private String details;
    private BigDecimal toBePaid;
    private long rentId;

    public static class PenaltyDtoBuilder {

        private long id;
        private PenaltyReason reason;
        private String details;
        private BigDecimal toBePaid;
        private long rentId;

        public PenaltyDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public PenaltyDtoBuilder reason(PenaltyReason reason) {
            this.reason = reason;
            return this;
        }

        public PenaltyDtoBuilder details(String details) {
            this.details = details;
            return this;
        }

        public PenaltyDtoBuilder toBePaid(BigDecimal toBePaid) {
            this.toBePaid = toBePaid;
            return this;
        }

        public PenaltyDtoBuilder rentId(long rentId) {
            this.rentId = rentId;
            return this;
        }

        public PenaltyDto build() {
            return new PenaltyDto(id, reason, details, toBePaid, rentId);
        }
    }

    private PenaltyDto(long id, PenaltyReason reason, String details, BigDecimal toBePaid, long rentId) {
        this.id = id;
        this.reason = reason;
        this.details = details;
        this.toBePaid = toBePaid;
        this.rentId = rentId;
    }

    public PenaltyDto() {
    }

    public long getId() {
        return id;
    }

    public PenaltyReason getReason() {
        return reason;
    }

    public String getDetails() {
        return details;
    }

    public BigDecimal getToBePaid() {
        return toBePaid;
    }

    public long getRentId() {
        return rentId;
    }
}
