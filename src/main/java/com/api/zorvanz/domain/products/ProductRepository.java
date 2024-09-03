package com.api.zorvanz.domain.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    boolean existsByName ( String name );
}
