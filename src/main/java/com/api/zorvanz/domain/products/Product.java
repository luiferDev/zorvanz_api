package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.Categories;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;
    private Integer stock;
    private Double popularity;
    private String imageUrl;
    
    public void setStock ( int i ) {
        this.stock = i;
    }
}
