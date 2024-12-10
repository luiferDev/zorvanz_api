package com.api.zorvanz.controller;

import com.api.zorvanz.domain.products.ProductListData;
import com.api.zorvanz.domain.products.ProductRepository;
import com.api.zorvanz.domain.products.RegisterProductData;
import com.api.zorvanz.domain.products.RegisterProductService;
import com.api.zorvanz.infra.errors.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( "/api/products" )
public class ProductController {

    private final ProductRepository productRepository;
    private final RegisterProductService registerProductService;

    public ProductController ( ProductRepository productRepository,
                               RegisterProductService registerProductService ) {
        this.productRepository = productRepository;
        this.registerProductService = registerProductService;
    }

    @GetMapping
    public ResponseEntity <Page <ProductListData>> getProducts (
            @PageableDefault ( page = 0, size = 8, sort = "popularity", direction = Sort.Direction.DESC )
            Pageable pagination ) {
        return ResponseEntity.ok ( productRepository.findAll ( pagination ).map ( ProductListData :: new ) );
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity <ProductListData> getProductById ( @PathVariable Long id ) {
        try {
            return productRepository.findById ( id ) // import java.util.Optional
                    .map ( product -> ResponseEntity.ok ( new ProductListData ( product ) ) )
                    .orElseThrow ( () -> new ResourceNotFoundException (
                            String.format ( "Product not found with ID: %d", id )
                    ) );
        } catch ( Exception e ) {
            return ResponseEntity.notFound ().build ();
        }
    }

    @PostMapping ( "/create-product" )
    @Transactional
    public ResponseEntity createProduct (
            @RequestBody @Valid RegisterProductData registerProduct ) {
        var response = registerProductService.registerProduct ( registerProduct );
        return ResponseEntity.ok ( response );
    }

    // TODO: Implementar método para actualizar un producto
    //@PutMapping ( "/update-product/{id}" )

    @DeleteMapping ( "/delete-product/{id}" )
    @Transactional
    public ResponseEntity deleteProduct ( @PathVariable Long id ) {
        return productRepository.findById ( id )
                .map ( product -> {
                    productRepository.deleteById ( id );
                    return ResponseEntity.ok ( "Producto eliminado correctamente" );
                } )
                .orElse ( ResponseEntity.notFound ().build () );
    }

    //TODO: Implementar método para buscar productos por nombre
    //@GetMapping ( "/search" )
    //TODO: actualizar producto
}
