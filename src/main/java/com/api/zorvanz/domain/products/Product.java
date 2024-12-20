package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.Categories;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.math.BigDecimal;

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
    private BigDecimal price = BigDecimal.ZERO;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;
    private Integer stock;
    private Double popularity;
    private String imageUrl;
    
    public void setStock ( int i ) {
        this.stock = i;
    }

    public void updateProduct ( @Valid UpdateProductDTO updateProduct ) {
        if ( updateProduct.name () != null ) {
            this.name = updateProduct.name ();
        }
        if ( updateProduct.description () != null ) {
            this.description = updateProduct.description ();
        }
        if ( updateProduct.price () != null ) {
            this.price = updateProduct.price ();
        }
        if ( updateProduct.category () != null ) {
            this.category = new Categories ( updateProduct.category ().categoryId (),
                    updateProduct.category ().categoryName () );
        }
        if ( updateProduct.stock () != null ) {
            this.stock = updateProduct.stock ();
        }
        if ( updateProduct.popularity () != null ) {
            this.popularity = updateProduct.popularity ();
        }
        if ( updateProduct.imageUrl () != null ) {
            this.imageUrl = updateProduct.imageUrl ();
        }
    }
}
