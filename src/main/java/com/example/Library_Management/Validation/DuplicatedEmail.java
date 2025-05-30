package com.example.Library_Management.Validation;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DuplicatedEmailValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicatedEmail {
    String message() default "Email is already existed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
