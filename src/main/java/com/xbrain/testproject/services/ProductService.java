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
        return productRepository.existsById(id);
    }

    public Product findProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = null;
        if(productOptional.isPresent()) {
            product = productOptional.get();
        }
        return product;
    }
}
