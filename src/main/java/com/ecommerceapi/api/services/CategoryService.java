package com.ecommerceapi.api.services;

import com.ecommerceapi.api.domain.category.Category;
import com.ecommerceapi.api.dtos.category.CategoryRequestDTO;
import com.ecommerceapi.api.dtos.category.CategoryResponseDTO;
import com.ecommerceapi.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequestDTO data) {
        Category newCategory = new Category();
        newCategory.setName(data.name());

        categoryRepository.save(newCategory);
        return newCategory;
    }

    public List<CategoryResponseDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        // Mapear cada entidade de Category para CategoryResponseDTO
        return categories.stream()
                .map(category -> new CategoryResponseDTO(category.getId(), category.getName()))
                .toList();


    }
}
