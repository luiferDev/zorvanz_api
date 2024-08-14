package com.api.zorvanz.controller;

import com.api.zorvanz.domain.products.ProductListData;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public ResponseEntity<Page<ProductListData>> getProducts(
            @PageableDefault(page = 0, size = 8, sort = { "popularity" })Pageable pagination ) {
        return ResponseEntity.ok( productRepository.findAll(pagination).map( ProductListData::new ) );
    }
}
