package com.agodgrab.carrental.mapper;

import com.agodgrab.carrental.domain.Penalty;
import com.agodgrab.carrental.dto.PenaltyDto;
import com.agodgrab.carrental.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PenaltyMapper implements Mapper<PenaltyDto, Penalty> {

    @Autowired
    RentMapper rentMapper;

    @Autowired
    RentService rentService;

    @Override
    public PenaltyDto mapToDto(Penalty penalty) {
        return new PenaltyDto.PenaltyDtoBuilder()
                .id(penalty.getId())
                .reason(penalty.getReason())
                .details(penalty.getDetails())
                .toBePaid(penalty.getToBePaid())
                .rentId(penalty.getRent().getId())
                .build();
    }

    @Override
    public Penalty mapToEntity(PenaltyDto penaltyDto) {
        return new Penalty(penaltyDto.getId(),
                penaltyDto.getReason(),
                penaltyDto.getDetails(),
                penaltyDto.getToBePaid(),
                rentService.findRent(penaltyDto.getRentId()));
    }
}
