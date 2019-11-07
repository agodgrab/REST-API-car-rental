package com.kodilla.kodilla.diplomaBackend.repository;

import com.kodilla.kodilla.diplomaBackend.domain.LogHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface LogHistoryRepository extends CrudRepository<LogHistory, Long> {

    @Override
    LogHistory save(LogHistory logHistory);
}
