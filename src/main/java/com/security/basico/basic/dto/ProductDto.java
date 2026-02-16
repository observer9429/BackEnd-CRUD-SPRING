package com.security.basico.basic.dto;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String nombre;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private String proveedor;


    public ProductDto() {
    }


    public ProductDto(Long id, String nombre, BigDecimal precioCompra, BigDecimal precioVenta, Integer stock,
            String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.proveedor = proveedor;
    }


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

    

    
    
}
