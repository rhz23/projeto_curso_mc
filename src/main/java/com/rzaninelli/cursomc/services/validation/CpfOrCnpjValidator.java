package com.rzaninelli.cursomc.services.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.lang.annotation.*;

//anotação opcional para validar CPF ou CNPJ

@ReportAsSingleViolation
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@Documented
public @interface CpfOrCnpjValidator {

    String message() default "Passed value is not valid CPF or CNPJ";

    Class<?> [] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
