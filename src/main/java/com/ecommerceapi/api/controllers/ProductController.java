package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.product.Product;
import com.ecommerceapi.api.domain.product.ProductRequestDTO;
import com.ecommerceapi.api.domain.product.ProductResponseDTO;
import com.ecommerceapi.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO body) {
        ProductResponseDTO newProduct = this.productService.createProduct(body);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> allProducts = this.productService.getProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductDetails(@PathVariable UUID productId) {
        ProductResponseDTO productDetails = this.productService.getProductDetails(productId);

        return ResponseEntity.ok(productDetails);

    }
}
