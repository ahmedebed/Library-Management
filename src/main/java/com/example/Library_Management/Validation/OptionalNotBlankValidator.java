package com.example.Library_Management.Validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalNotBlankValidator implements ConstraintValidator<OptionalNotBlank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || !value.trim().isEmpty();
    }
}