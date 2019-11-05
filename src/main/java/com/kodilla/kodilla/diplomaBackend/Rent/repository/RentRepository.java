package com.kodilla.kodilla.diplomaBackend.Rent.repository;

import com.kodilla.kodilla.diplomaBackend.Rent.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    Rent save(Rent rent);

    List<Rent> getRentsByUserId(long id);

    List<Rent> findAll();

}
