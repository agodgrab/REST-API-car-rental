package com.kodilla.kodilla.diplomaBackend.repository;

import com.kodilla.kodilla.diplomaBackend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    Optional<User> findByMail(String mail);

    Optional<User> findByLogin(String login);

}
