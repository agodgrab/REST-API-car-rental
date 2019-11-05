package com.kodilla.kodilla.diplomaBackend.Penalty.mapper;

import com.kodilla.kodilla.diplomaBackend.Penalty.domain.Penalty;
import com.kodilla.kodilla.diplomaBackend.Penalty.dto.PenaltyDto;
import com.kodilla.kodilla.diplomaBackend.Rent.mapper.RentMapper;
import com.kodilla.kodilla.diplomaBackend.Rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PenaltyMapper {

    @Autowired
    RentMapper rentMapper;

    @Autowired
    RentService rentService;

    public PenaltyDto mapToPenaltyDto(Penalty penalty){
        return new PenaltyDto.PenaltyDtoBuilder()
                .id(penalty.getId())
                .reason(penalty.getReason())
                .details(penalty.getDetails())
                .toBePaid(penalty.getToBePaid())
                .rentId(penalty.getRent().getId())
                .build();
    }

    public Penalty mapToPenalty(PenaltyDto penaltyDto){
        return new Penalty(penaltyDto.getId(),
                penaltyDto.getReason(),
                penaltyDto.getDetails(),
                penaltyDto.getToBePaid(),
                rentService.findRent(penaltyDto.getRentId()));
    }
}
