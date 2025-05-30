package com.example.Library_Management.dto.request;

import com.example.Library_Management.Validation.DuplicatedEmail;
import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.Validation.OptionalNotBlank;
import com.example.Library_Management.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record UserRequest(
        @NotBlank(message = "First name cannot be blank", groups = {OnCreate.class})
        @Pattern(
                regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
                message = "First name must contain only letters and single spaces between words.",
                groups = {OnCreate.class, OnUpdate.class}
        )

        @OptionalNotBlank(groups = {OnUpdate.class})
        String firstname,

        @NotBlank(message = "Last name cannot be blank", groups = {OnCreate.class})
        @Size(max = 50, message = "Last name must be at most 50 characters long", groups = {OnCreate.class, OnUpdate.class})
        @Pattern(
                regexp = "^[A-Za-z]+( [A-Za-z]+)*$",
                message = "First name must contain only letters and single spaces between words.",
                groups = {OnCreate.class, OnUpdate.class}
        )
        @OptionalNotBlank(groups = {OnUpdate.class})
        String lastname,
        @NotBlank(message = "Email cannot be blank", groups = {OnCreate.class})
        @Email(message = "Invalid email format", groups = {OnCreate.class, OnUpdate.class})
        @DuplicatedEmail(groups = {OnCreate.class, OnUpdate.class})
        @Size(max = 100, message = "email must be at most 100 characters long", groups = {OnCreate.class, OnUpdate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String email,

        @Size(max = 100, message = "Password must be at most 100 characters long", groups = {OnCreate.class, OnUpdate.class})
        @Pattern(
                groups = {OnCreate.class, OnUpdate.class},
                regexp = "^$|^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[*^%@&$#!]).{8,}$",
                message = "Password must be at least 8 characters long and include uppercase and lowercase letters, digits, and special characters (*^%@&$#!.)"
        )
        @NotBlank(message = "Password is required", groups = {OnCreate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String password,
        @NotNull(message = "Role is required", groups = {OnCreate.class})
        Role role
) {
}
