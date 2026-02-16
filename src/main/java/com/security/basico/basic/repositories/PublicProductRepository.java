package com.security.basico.basic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.basico.basic.entities.PublicProduct;

public interface PublicProductRepository extends JpaRepository<PublicProduct, Long> {
    // SOLO LECTURA (findAll, findById)
}
