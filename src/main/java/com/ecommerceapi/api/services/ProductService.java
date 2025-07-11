package com.ecommerceapi.api.services;

import com.ecommerceapi.api.domain.product.Product;
import com.ecommerceapi.api.domain.product.ProductRequestDTO;
import com.ecommerceapi.api.domain.product.ProductResponseDTO;
import com.ecommerceapi.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequestDTO data) {
        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());
        newProduct.setPrice(data.price());
        newProduct.setCategory(data.category());
        newProduct.setStock(data.stock());

        productRepository.save(newProduct);

        return newProduct;
    }

    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        // Mapear cada entidade Product para ProductResponseDTO
        return products.stream()
                .map(product -> new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory(), product.getStock()))
                .toList();
    }

}
