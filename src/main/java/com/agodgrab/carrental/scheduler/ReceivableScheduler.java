package com.kodilla.kodilla.diplomaBackend.scheduler;

import com.kodilla.kodilla.diplomaBackend.repository.RentRepository;
import com.kodilla.kodilla.diplomaBackend.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ReceivableScheduler {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    RentService rentService;

    @Scheduled(cron = "0 0 6 * * *")
    public void accountReceivables() {
        rentRepository.findAll().stream()
                .filter(rent -> ("BOOKED").equals(rent.getStatus()))
                .filter(rent -> rent.getToBePaid() == null)
                .filter(rent -> ChronoUnit.DAYS.between(LocalDate.now(), rent.getStartDay()) < 3)
                .forEach(rent -> rentService.accountFor(rent));
    }
}
