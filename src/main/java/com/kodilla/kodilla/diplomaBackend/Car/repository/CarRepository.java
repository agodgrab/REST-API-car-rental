package com.kodilla.kodilla.diplomaBackend.Car.repository;

import com.kodilla.kodilla.diplomaBackend.Car.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Query(nativeQuery = true)
    List<Car> searchCarInDateRange(@Param("REQUESTED_START") LocalDate requestedStart,
                                   @Param("REQUESTED_END") LocalDate requestedEnd,
                                   @Param("REQUESTED_CATEGORY") long categoryId);

    Car save(Car car);

    @Override
    void deleteById(Long id);
}
