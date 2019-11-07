package com.kodilla.kodilla.diplomaBackend.controller;

import com.kodilla.kodilla.diplomaBackend.domain.Category;
import com.kodilla.kodilla.diplomaBackend.dto.CategoryDto;
import com.kodilla.kodilla.diplomaBackend.mapper.CategoryMapper;
import com.kodilla.kodilla.diplomaBackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping(value = "/categories")
    public List<Category> showAllCategories() {
        return categoryService.getCategories();
    }

    @PostMapping(value = "/category")
    public CategoryDto addNewCategory(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.mapToCategoryDto(categoryService.addCategory(categoryMapper.mapToCategory(categoryDto)));
    }

    @PutMapping(value = "/category")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.mapToCategoryDto(categoryService.updateCategory(categoryMapper.mapToCategory(categoryDto)));
    }

    @DeleteMapping(value = "/category")
    public void deleteCategory(@RequestParam long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
