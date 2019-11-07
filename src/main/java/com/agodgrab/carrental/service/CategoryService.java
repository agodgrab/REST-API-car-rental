package com.kodilla.kodilla.diplomaBackend.service;

import com.kodilla.kodilla.diplomaBackend.domain.Category;
import com.kodilla.kodilla.diplomaBackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LogHistoryService logHistoryService;

    @Autowired
    private UserService userService;

    public Category getCategory(String name) {
        return categoryRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        logHistoryService.saveLog(userService.getAdmin(), "New car category created: " + category.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        logHistoryService.saveLog(userService.getAdmin(), "Update category: " + category.getId());
        return categoryRepository.save(category);
    }

    public void deleteCategory(long id) {
        logHistoryService.saveLog(userService.getAdmin(), "Removal of category: " + id);
        categoryRepository.deleteById(id);
    }

    public Category findCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }


}
