package com.example.Library_Management.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePublisherNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePublisherName {
    String message() default "Publisher name must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
