package com.ecommerceapi.api.repositories;

import com.ecommerceapi.api.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN p.category c " +
            "WHERE (:name IS NULL OR :name = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:minStock IS NULL OR p.stock >= :minStock) " +
            "AND (:maxStock IS NULL OR p.stock <= :maxStock) " +
            "AND (:categoryName IS NULL OR c.name = :categoryName)")
    List<Product> findFilteredProducts(@Param("name") String name,
                                        @Param("minPrice") BigDecimal minPrice,
                                        @Param("maxPrice") BigDecimal maxPrice,
                                        @Param("minStock") Integer minStock,
                                        @Param("maxStock") Integer maxStock,
                                        @Param("categoryName") String categoryName
    );

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    List<Product> findByName(@Param("name") String name);

}
