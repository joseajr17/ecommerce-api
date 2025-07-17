package com.ecommerceapi.api.services;

import com.ecommerceapi.api.domain.category.Category;
import com.ecommerceapi.api.domain.category.CategoryResponseDTO;
import com.ecommerceapi.api.domain.product.Product;
import com.ecommerceapi.api.domain.product.ProductRequestDTO;
import com.ecommerceapi.api.domain.product.ProductResponseDTO;
import com.ecommerceapi.api.repositories.CategoryRepository;
import com.ecommerceapi.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO data) {
        // Buscar categoria passada no JSON
        Category category = this.categoryRepository.findById(data.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());
        newProduct.setPrice(data.price());
        newProduct.setCategory(category);
        newProduct.setStock(data.stock());

        productRepository.save(newProduct);

        return toProductResponseDTO(newProduct);
    }

    private ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new CategoryResponseDTO(product.getCategory().getId(), product.getCategory().getName()),
                product.getStock()
        );
    }

    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        // Mapear cada entidade Product para ProductResponseDTO
        return products.stream()
                .map(this::toProductResponseDTO)
                .toList();
    }

}
