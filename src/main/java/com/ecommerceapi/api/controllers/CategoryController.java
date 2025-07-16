package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.category.Category;
import com.ecommerceapi.api.domain.category.CategoryRequestDTO;
import com.ecommerceapi.api.domain.category.CategoryResponseDTO;
import com.ecommerceapi.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO body) {
        Category newCategory = this.categoryService.createCategory(body);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        List<CategoryResponseDTO> allCategories = this.categoryService.getCategories();
        return ResponseEntity.ok(allCategories);
    }
}
