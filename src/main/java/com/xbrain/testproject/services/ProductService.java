package com.xbrain.testproject.services;

import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean existProduct(Long id){
        if (id != null) {
            return productRepository.existsById(id);
        }
        return false;
    }

    public Product findProductById(Long id){
        Product product = null;

        if (id != null) {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                product = productOptional.get();
            }
        }
        return product;
    }
}
