package com.example.Library_Management.Validation;

import com.example.Library_Management.repository.PublisherRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePublisherNameValidator implements ConstraintValidator<UniquePublisherName, String> {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null || name.isBlank()) {
            return true;
        }
        return !publisherRepository.existsByName(name);
    }
}
