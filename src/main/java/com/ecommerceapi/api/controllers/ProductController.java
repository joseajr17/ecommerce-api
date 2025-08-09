package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.dtos.product.ProductRequestDTO;
import com.ecommerceapi.api.dtos.product.ProductResponseDTO;
import com.ecommerceapi.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO body) {
        ProductResponseDTO newProduct = this.productService.createProduct(body);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequestDTO body) {
        ProductResponseDTO productUpdated = this.productService.updateProduct(productId, body);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
