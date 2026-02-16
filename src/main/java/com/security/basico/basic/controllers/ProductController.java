package com.security.basico.basic.controllers;

import com.security.basico.basic.dto.ProductDto;
import com.security.basico.basic.dto.ProductRequestDto;
import com.security.basico.basic.dto.PublicProductDto;
import com.security.basico.basic.dto.UpdateStockDto;

import com.security.basico.basic.services.ProductService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
 
    // =========================
    // ADMIN - CRUD privado
    // =========================

    @GetMapping("/admin/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllPrivateProducts());
    }

    @GetMapping("/admin/products/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {

    return productService.findProductById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    //crear
    @PostMapping("/admin/products")
    public ResponseEntity<ProductDto> createProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        return ResponseEntity.status(201)
                .body(productService.save(dto));
    }

    //actualizar
     @PutMapping("/admin/products/{id}/stock")
    public ResponseEntity<ProductDto> updateStock(
            @PathVariable Long id,
            @RequestBody UpdateStockDto dto) {

        return ResponseEntity.ok(productService.updateStock(id, dto));
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // =========================
    // PUBLICO - SOLO LECTURA
    // =========================

    @GetMapping("public/products")
    public List<PublicProductDto> list() {
        return productService.findAllPublicProducts();
    }

    
    @GetMapping("/public/products/{id}")
    public ResponseEntity<PublicProductDto> findPublicProductById(@PathVariable Long id) {

    return productService.findProductPublicById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    

    


   


    

    
}

