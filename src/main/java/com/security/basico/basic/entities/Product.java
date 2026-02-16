package com.security.basico.basic.entities;

import java.math.BigDecimal;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@PrecioVentaMayor // 👈 validación a nivel de clase
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @NotBlank(message = "{NotBlank.product.nombre}")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "{NotNull.product.precioCompra}")
    @DecimalMin(value = "0.01", message = "{Min.product.precioCompra}")
    @Column(name = "precio_compra", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioCompra;

    @NotNull(message = "{NotNull.product.precioVenta}")
    @DecimalMin(value = "0.01", message = "{Min.product.precioVenta}")
    @Column(name = "precio_venta", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioVenta;

    @NotNull(message = "{NotNull.product.stock}")
    @Min(value = 0, message = "{Min.product.stock}")
    @Column(nullable = false)
    private Integer stock;

    @NotBlank(message = "{NotBlank.product.proveedor}")
    @Column(nullable = false)
    private String proveedor;

    //@JsonBackReference // indica a donde se referencia, para que no se aniden lso json
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private PublicProduct publicProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public PublicProduct getPublicProduct() {
        return publicProduct;
    }

    public void setPublicProduct(PublicProduct publicProduct) {
        this.publicProduct = publicProduct;
    }

    // getters y setters

    

    
}

