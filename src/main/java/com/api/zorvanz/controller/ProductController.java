package com.api.zorvanz.controller;

import com.api.zorvanz.domain.category.CategoryData;
import com.api.zorvanz.domain.products.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RegisterProductService registerProductService;
    
    @GetMapping
    public ResponseEntity<Page<ProductListData>> getProducts(
            @PageableDefault( page = 0, size = 8, sort = "popularity" )Pageable pagination ) {
        return ResponseEntity.ok( productRepository.findAll( pagination ).map( ProductListData::new ) );
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductListData> getProductById( @PathVariable Long id ) {
        Product product = productRepository.getReferenceById( id );
        return ResponseEntity.ok( new ProductListData( product ) );
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity createProduct(
            @RequestBody @Valid RegisterProductData registerProduct) {
        var response = registerProductService.registerProduct( registerProduct );
        return ResponseEntity.ok( response );
    }
}
