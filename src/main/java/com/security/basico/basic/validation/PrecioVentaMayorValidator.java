package com.security.basico.basic.validation;

import com.security.basico.basic.dto.ProductRequestDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//@PrecioVentaMayor + PrecioVentaMayorValidator
//la anotación define la regla
//el validator define cómo se valida
//Spring los conecta automáticamente


public class PrecioVentaMayorValidator
        implements ConstraintValidator<PrecioVentaMayor, ProductRequestDto> {

    @Override
    public boolean isValid(ProductRequestDto dto,
                           ConstraintValidatorContext context) {

        if (dto == null) return true;

        //sta validación NO es para verificar null, Es para verificar relación entre dos valores
        //Si devuelves false aquí:, Mostrarías el mensaje de PrecioVentaMayor, Y ocultarías el mensaje real (@NotNull)
        if (dto.getPrecioCompra() == null || dto.getPrecioVenta() == null) {
            return true; // @NotNull ya se encarga
        }

        //devuelve -1 si el priemr valor es mayor al segundo
        //devuelve 0: si son del mismo valor
        //devuelve 1 si el segundo valor es mayor al primero
        return dto.getPrecioVenta()
                .compareTo(dto.getPrecioCompra()) > 0;
    }
}
