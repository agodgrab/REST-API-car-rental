package com.kodilla.kodilla.diplomaBackend.Penalty.controller;

import com.kodilla.kodilla.diplomaBackend.Penalty.dto.PenaltyDto;
import com.kodilla.kodilla.diplomaBackend.Penalty.mapper.PenaltyMapper;
import com.kodilla.kodilla.diplomaBackend.Penalty.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;

    @Autowired
    private PenaltyMapper penaltyMapper;

    @PostMapping(value="/penalty", consumes = APPLICATION_JSON_VALUE )
    public void declarePenalty(@RequestBody PenaltyDto penaltyDto){
        penaltyService.chargePenalty(penaltyMapper.mapToPenalty(penaltyDto));
    }

    @DeleteMapping(value ="/penalty/{id}")
    public void deletePenalty(@PathVariable("id") long id){
        penaltyService.cancelPenalty(id);
    }
}
