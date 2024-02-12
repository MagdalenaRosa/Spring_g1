package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);
}
