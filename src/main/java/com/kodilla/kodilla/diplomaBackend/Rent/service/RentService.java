package com.kodilla.kodilla.diplomaBackend.Rent.service;

import com.kodilla.kodilla.diplomaBackend.Penalty.domain.Penalty;
import com.kodilla.kodilla.diplomaBackend.Rent.domain.Rent;
import com.kodilla.kodilla.diplomaBackend.User.domain.User;
import com.kodilla.kodilla.diplomaBackend.Rent.domain.RentStatus;
import com.kodilla.kodilla.diplomaBackend.Rent.repository.RentRepository;
import com.kodilla.kodilla.diplomaBackend.LogHistory.service.LogHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private LogHistoryService logHistoryService;

    private static int PRICE_FOR_INSURANCE = 4;
    private static int PRICE_FOR_EXTRA_TRUNK = 10;

    public Rent makeReservation(Rent rent){
        rent.setStatus(RentStatus.BOOKED);
        logHistoryService.saveLog(rent.getUser(), "Booking car (" + rent.getCarRented().getId() + ")");
        return rentRepository.save(rent);
    }

    public Rent confirmRent(Rent rent){
        rent.setStatus(RentStatus.RENTED);
        logHistoryService.saveLog(rent.getUser(), "Renting car (" + rent.getCarRented().getId() + ")");
        return rentRepository.save(rent);
    }

    public Rent cancelReservation(Rent rent){
        rent.setStatus(RentStatus.CANCELED);
        logHistoryService.saveLog(rent.getUser(), "Cancelling reservation number " + rent.getId());
        return rentRepository.save(rent);
    }

    public Rent confirmReturn(Rent rent){
        rent.setStatus(RentStatus.FINISHED);
        logHistoryService.saveLog(rent.getUser(), "Car (" + rent.getCarRented().getId() + ") return");
        return rentRepository.save(rent);
    }

    public Rent applyPenalty(Penalty penalty){
        BigDecimal updatedToBePaid;
        updatedToBePaid = penalty.getRent().getToBePaid().add(penalty.getToBePaid());
        penalty.getRent().setToBePaid(updatedToBePaid);
        penalty.getRent().getListOfPenalties().add(penalty);
        return rentRepository.save(penalty.getRent());
    }

    public Rent recallPenalty(Penalty penalty){
        BigDecimal updatedToBePaid;
        updatedToBePaid = penalty.getRent().getToBePaid().subtract(penalty.getToBePaid());
        penalty.getRent().getListOfPenalties().remove(penalty);
        penalty.getRent().setToBePaid(updatedToBePaid);
        return rentRepository.save(penalty.getRent());
    }

    public List<Rent> fetchUserRents(User user){

        if("ADMIN".equals(user.getRole())){
            return rentRepository.findAll();
        }
        else {
            return rentRepository.getRentsByUserId(user.getId());
        }
    }

    public void accountFor(Rent rent){

        BigDecimal totalPricePerDay = rent.getCarRented().getCategory().getPricePerDay();

        if(rent.isWithExtraCarTrunk()){
            totalPricePerDay = totalPricePerDay.add(new BigDecimal(PRICE_FOR_EXTRA_TRUNK));
        }
        if(rent.isWithInsurance()){
            totalPricePerDay = totalPricePerDay.add(new BigDecimal(PRICE_FOR_INSURANCE));
        }

        BigDecimal durationOfRent = new BigDecimal(ChronoUnit.DAYS.between(rent.getStartDay(), rent.getEndDay()));

        rent.setToBePaid(totalPricePerDay.multiply(durationOfRent));
        logHistoryService.saveLog(rent.getUser(), "Price to be paid has been updated. Rent id: " + rent.getId());
        rentRepository.save(rent);
    }

    public Rent findRent(long id){
        return rentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
