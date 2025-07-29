package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.product.ProductRequestDTO;
import com.ecommerceapi.api.domain.product.ProductResponseDTO;
import com.ecommerceapi.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> allProducts = this.productService.getProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductDetails(@PathVariable UUID productId) {
        ProductResponseDTO productDetails = this.productService.getProductDetails(productId);
        return ResponseEntity.ok(productDetails);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(@RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) BigDecimal minPrice,
                                                                   @RequestParam(required = false) BigDecimal maxPrice,
                                                                   @RequestParam(required = false) String categoryName,
                                                                   @RequestParam(required = false) Integer minStock,
                                                                   @RequestParam(required = false) Integer maxStock) {
        List<ProductResponseDTO> products = this.productService.getFilteredProducts(name, minPrice, maxPrice, categoryName, minStock, maxStock);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/admin/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO body) {
        ProductResponseDTO newProduct = this.productService.createProduct(body);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequestDTO body) {
        ProductResponseDTO productUpdated = this.productService.updateProduct(productId, body);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
