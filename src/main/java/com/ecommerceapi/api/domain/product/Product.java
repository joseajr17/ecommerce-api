package com.ecommerceapi.api.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    // To Do: Criar a entidade de Category
    private String category;
    private int stock;
    // To Do: Adicionar Imagem a um Produto

    public Product(ProductRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.category = data.category();
        this.stock = data.stock();
    }
}
