package com.security.basico.basic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.basico.basic.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // CRUD completo para ADMIN
    
   
}