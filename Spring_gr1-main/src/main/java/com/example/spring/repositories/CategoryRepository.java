package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.model.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Integer> {

    boolean existsByName(String name);
}
