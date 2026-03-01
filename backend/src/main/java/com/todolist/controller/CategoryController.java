package com.todolist.controller;

import com.todolist.model.Category;
import com.todolist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam Long userId) {
        List<Category> categories = categoryService.getAllCategories(userId);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestParam Long userId) {
        category.setUserId(userId);
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id, @RequestParam Long userId) {
        categoryService.deleteCategory(id, userId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}