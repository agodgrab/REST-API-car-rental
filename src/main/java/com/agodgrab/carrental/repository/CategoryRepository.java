package com.kodilla.kodilla.diplomaBackend.repository;

import com.kodilla.kodilla.diplomaBackend.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


    Optional<Category> findByName(String name);

    Optional<Category> findById(long id);

    List<Category> findAll();

    Category save(Category category);

    void deleteById(long id);


}
