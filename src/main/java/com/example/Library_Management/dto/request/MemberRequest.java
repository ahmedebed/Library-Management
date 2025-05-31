package com.example.Library_Management.dto.request;

import com.example.Library_Management.Validation.DuplicatedEmail;
import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.Validation.OptionalNotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MemberRequest(

        @NotBlank(message = "First name is required", groups = {OnCreate.class})
        @OptionalNotBlank(groups = OnUpdate.class)
        String firstname,

        @NotBlank(message = "Last name is required", groups = {OnCreate.class})
        @OptionalNotBlank(groups = OnUpdate.class)
        String lastname,

        @NotBlank(message = "Email is required", groups = {OnCreate.class})
        @Email(message = "Invalid email format", groups = {OnCreate.class, OnUpdate.class})
        @DuplicatedEmail(groups = {OnCreate.class})
        @OptionalNotBlank(groups = OnUpdate.class)
        String email,
        String address
) {}
