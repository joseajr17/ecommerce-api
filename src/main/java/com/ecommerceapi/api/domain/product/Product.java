package com.ecommerceapi.api.domain.product;

import com.ecommerceapi.api.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name="product")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    // Vários produtos podem estar contido em uma categoria
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    // To Do: Adicionar Imagem a um Produto

    public Product(ProductRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.category = data.category();
        this.stock = data.stock();
    }
}
