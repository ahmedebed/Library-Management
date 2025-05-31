package com.example.Library_Management.Validation;

import com.example.Library_Management.repository.MemberRepository;
import com.example.Library_Management.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DuplicatedEmailValidator implements ConstraintValidator<DuplicatedEmail, String> {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true;
        }

        return !userRepository.existsByEmail(email) && !memberRepository.existsByEmail(email);
    }
}
