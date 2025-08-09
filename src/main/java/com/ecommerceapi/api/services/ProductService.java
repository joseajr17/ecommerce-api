package com.ecommerceapi.api.services;

import com.ecommerceapi.api.domain.category.Category;
import com.ecommerceapi.api.dtos.category.CategoryResponseDTO;
import com.ecommerceapi.api.domain.product.Product;
import com.ecommerceapi.api.dtos.product.ProductRequestDTO;
import com.ecommerceapi.api.dtos.product.ProductResponseDTO;
import com.ecommerceapi.api.repositories.CategoryRepository;
import com.ecommerceapi.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO data) {

        validateProductData(data);

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

    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        // Mapear cada entidade Product para ProductResponseDTO
        return products.stream()
                .map(this::toProductResponseDTO)
                .toList();
    }

    public ProductResponseDTO getProductDetails(UUID productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return toProductResponseDTO(product);
    }

    public List<ProductResponseDTO> getFilteredProducts(String name, BigDecimal minPrice, BigDecimal maxPrice, String categoryName, Integer minStock, Integer maxStock) {
        if (name != null && name.trim().isEmpty()) name = null;
        if (categoryName != null && categoryName.trim().isEmpty()) categoryName = null;

        List<Product> filtered = productRepository.findFilteredProducts(name, minPrice, maxPrice, minStock, maxStock, categoryName);
        return filtered.stream().map(this::toProductResponseDTO).toList();
    }

    public ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO data) {

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        validateProductData(data);

        product.setName(data.name() == null ? product.getName() : data.name());
        product.setDescription(data.description() == null ? product.getDescription() : data.description());
        BigDecimal newPrice = data.price() == null ? product.getPrice() : data.price();
        product.setPrice(newPrice);
        int newStock = data.stock() == null ? product.getStock() : data.stock();
        product.setStock(newStock);

        Category newCategory = product.getCategory();
        if(data.categoryId() != null) {
            newCategory = this.categoryRepository.findById(data.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        }
        product.setCategory(newCategory);

        productRepository.save(product);

        return toProductResponseDTO(product);
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

    private boolean productNameExists(String name) {
        return !productRepository.findByName(name).isEmpty();
    }

    private void validateProductData(ProductRequestDTO data) {
        if (productNameExists(data.name())) {
            throw new IllegalArgumentException("Product with this name already exists.");
        }

        if (data.price() == null || data.price().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (data.stock() != null && data.stock() < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }

        if (data.categoryId() == null) {
            throw new IllegalArgumentException("Product must be associated with a category.");
        }
    }

    public void deleteProduct(UUID productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepository.delete(product);
    }

}
