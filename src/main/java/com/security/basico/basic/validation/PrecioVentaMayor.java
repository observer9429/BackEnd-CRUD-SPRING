package com.security.basico.basic.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PrecioVentaMayorValidator.class) //indicamos que clase validará
@Target(ElementType.TYPE) // 👈 CLASE, solo en clases
@Retention(RetentionPolicy.RUNTIME)
public @interface PrecioVentaMayor {

    String message() default "{PrecioVentaMayor.product}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
