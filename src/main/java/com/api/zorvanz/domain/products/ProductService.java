package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.CategoriesRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Async ( "threadPoolTaskExecutor" )
    @Transactional
    public CompletableFuture <ProductListData> registerProduct ( RegisterProductData data ) {

        if ( productRepository.existsByName ( data.name () ) ) {
            throw new ValidationException ( "Nombre de Producto existente" );
        }

        if ( !categoriesRepository.existsById ( data.categoryId () )
                || categoriesRepository.findById ( data.categoryId () ).isEmpty () ) {
            throw new ValidationException ( "Categoria no existente" );
        }

        if ( data.popularity () < 0 || data.popularity () > 5 ) {
            throw new ValidationException ( "La popularidad debe estar entre 0 y 5" );
        }

        var category = categoriesRepository.findById ( data.categoryId () ).get ();

        var product = new Product ( null, data.name (), data.description (),
                data.price (), category, data.stock (), data.popularity (), data.imageUrl () );

        productRepository.save ( product );

        var productList = new ProductListData ( product );

        return CompletableFuture.completedFuture ( productList );
    }
}
