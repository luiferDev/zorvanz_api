package com.api.zorvanz.domain.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository < Product, Long > {
    boolean existsByName ( String name );

    @Query ( "SELECT p.price FROM Product p WHERE p.id IN :productIds" )
    List < Double > findPricesByProductIds ( List < Long > productIds );

    List < Product > findByNameStartingWithIgnoreCase ( String name );
}
