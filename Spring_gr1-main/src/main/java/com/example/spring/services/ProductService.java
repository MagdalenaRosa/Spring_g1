package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring.model.Product;
import com.example.spring.repositories.ProductRepository;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getDatabase() {
        return productRepository.findAll();
    }

    public boolean existProductByName(Product productForm) {
        return productRepository.existsByName(productForm.getName());
    }

    public void insertProduct(Product productForm) {
        if (!existProductByName(productForm)) {
            productForm.setId(null);
            productRepository.save(productForm);
        }
    }

    public void removeProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public Optional<Product> findProduct(Integer productId) {
        return productRepository.findById(productId);

    }

    public void updateProduct(Product productForm, Integer productId) {
        productForm.setId(productId);
        productRepository.save(productForm);

    }
}
