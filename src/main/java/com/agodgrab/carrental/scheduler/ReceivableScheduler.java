package com.agodgrab.carrental.scheduler;

import com.agodgrab.carrental.domain.enums.RentStatus;
import com.agodgrab.carrental.repository.RentRepository;
import com.agodgrab.carrental.service.RentService;
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

    private static final String SIX_O_CLOCK_OF_EVERY_DAY = "0 0 6 * * *";

    @Scheduled(cron = SIX_O_CLOCK_OF_EVERY_DAY)
    public void accountReceivables() {
        rentRepository.findAll().stream()
                .filter(rent -> RentStatus.BOOKED.equals(rent.getStatus()))
                .filter(rent -> rent.getToBePaid() == null)
                .filter(rent -> ChronoUnit.DAYS.between(LocalDate.now(), rent.getStartDay()) < 3)
                .forEach(rent -> rentService.accountFor(rent));
    }
}
