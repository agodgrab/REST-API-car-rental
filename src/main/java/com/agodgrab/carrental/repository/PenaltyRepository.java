package com.agodgrab.carrental.repository;

import com.agodgrab.carrental.domain.Penalty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface PenaltyRepository extends CrudRepository<Penalty, Long> {

    @Override
    Penalty save(Penalty penalty);

    void deleteById(long id);

    Optional<Penalty> findById(long id);
}
