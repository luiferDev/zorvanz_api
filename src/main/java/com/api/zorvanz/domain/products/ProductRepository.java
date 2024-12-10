package com.api.zorvanz.domain.products;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    boolean existsByName ( String name );
    
    @Query ( "SELECT p.price FROM Product p WHERE p.id IN :productIds" )
    List <Double> findPricesByProductIds ( List <Long> productIds );
}
