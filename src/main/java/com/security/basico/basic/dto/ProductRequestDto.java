package com.security.basico.basic.dto;

import java.math.BigDecimal;

import com.security.basico.basic.validation.PrecioVentaMayor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//clase que servira para validar si cumple las validacioens
//antes de enviar a la base de datos

@PrecioVentaMayor
public class ProductRequestDto {

    @NotBlank(message = "{NotBlank.product.nombre}")
    private String nombre;

    @NotNull(message = "{NotNull.product.precioCompra}")
    @DecimalMin(value = "0.01", message = "{Min.product.precioCompra}")
    private BigDecimal precioCompra;

    @NotNull(message = "{NotNull.product.precioVenta}")
    @DecimalMin(value = "0.01", message = "{Min.product.precioVenta}")
    private BigDecimal precioVenta;

    @NotNull(message = "{NotNull.product.stock}")
    @Min(value = 0, message = "{Min.product.stock}")
    private Integer stock;

    @NotBlank(message = "{NotBlank.product.proveedor}")
    private String proveedor;

    

    public ProductRequestDto() {
    }


    
    public ProductRequestDto(String nombre, BigDecimal precioCompra, BigDecimal precioVenta, Integer stock,
            String proveedor) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.proveedor = proveedor;
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
