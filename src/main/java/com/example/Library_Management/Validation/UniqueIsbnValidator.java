package com.example.Library_Management.Validation;

import com.example.Library_Management.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueIsbnValidator implements ConstraintValidator<UniqueIsbn, String> {

    private final BookRepository bookRepository;

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        if (isbn == null) return true;
        return !bookRepository.existsByIsbn(isbn);
    }
}
