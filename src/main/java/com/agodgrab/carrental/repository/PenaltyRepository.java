package com.kodilla.kodilla.diplomaBackend.repository;

import com.kodilla.kodilla.diplomaBackend.domain.Penalty;
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
