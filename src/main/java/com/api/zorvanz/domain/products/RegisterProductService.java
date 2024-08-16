package com.api.zorvanz.domain.products;

import com.api.zorvanz.domain.category.CategoriesRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterProductService {
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    
    public ProductListData registerProduct( RegisterProductData data ) {
        
        //TODO: no dejar que se agreguen productos con el mismo nombre
        
        if ( ! categoriesRepository.existsById( data.categoryId() )
                ||categoriesRepository.findById( data.categoryId() ).isEmpty() ) {
            throw new ValidationException( "Categoria no existente" );
        }
        
        var category = categoriesRepository.findById( data.categoryId() ).get();
        
        var product = new Product( null, data.name(), data.description(),
                data.price(), category, data.stock(), data.popularity(), data.imageUrl() );
        
        productRepository.save( product );
        
        return new ProductListData( product );
    }
}
