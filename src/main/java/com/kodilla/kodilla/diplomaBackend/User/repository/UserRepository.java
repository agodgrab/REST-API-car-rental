package com.kodilla.kodilla.diplomaBackend.User.repository;

import com.kodilla.kodilla.diplomaBackend.User.domain.User;
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
