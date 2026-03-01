package com.todolist.service;

import com.todolist.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories(Long userId);
    Category getCategoryById(Long id, Long userId);
    Category createCategory(Category category);
    void deleteCategory(Long id, Long userId);
}