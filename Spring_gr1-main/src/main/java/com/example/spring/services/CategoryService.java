package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring.model.ProductCategory;
import com.example.spring.repositories.CategoryRepository;

@Service
public class CategoryService {
    public final CategoryRepository categoryRepository;

    public List<ProductCategory> findAllCategoryProduct() {
        return categoryRepository.findAll();
    }

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ProductCategory> getDatabase() {
        return categoryRepository.findAll();
    }

    public boolean existCategoryByName(ProductCategory productForm) {
        return categoryRepository.existsByName(productForm.getName());
    }

    public void insertCategory(ProductCategory productForm) {
        if (!existCategoryByName(productForm)) {
            productForm.setId(null);
            categoryRepository.save(productForm);
        }
    }

    public void removeCategory(Integer productId) {
        categoryRepository.deleteById(productId);
    }

    public Optional<ProductCategory> findCategory(Integer productId) {
        return categoryRepository.findById(productId);

    }

    public void updateCategory(Integer productId, ProductCategory productForm) {
        productForm.setId(productId);
        categoryRepository.save(productForm);

    }

}
