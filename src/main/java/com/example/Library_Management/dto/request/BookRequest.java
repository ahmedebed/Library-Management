package com.example.Library_Management.dto.request;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.Validation.OptionalNotBlank;
import com.example.Library_Management.Validation.UniqueIsbn;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;

@Builder
public record BookRequest(

        @NotBlank(message = "Title must not be blank", groups = OnCreate.class)
        @OptionalNotBlank(groups = OnUpdate.class)
        String title,

        String summary,

        @Pattern(regexp = "\\d{10}|\\d{13}", message = "ISBN must be 10 or 13 digits", groups = {OnCreate.class, OnUpdate.class})
        @UniqueIsbn(groups = OnCreate.class)
        @OptionalNotBlank(groups = OnUpdate.class)
        String isbn,

        @Min(value = 1000, message = "Year must be after 1000", groups = {OnCreate.class, OnUpdate.class})
        @Max(value = 2030, message = "Year must be before 2100", groups = {OnCreate.class, OnUpdate.class})
        Integer publicationYear,

        String language,

        String edition,

        String image,

        @NotNull(message = "Publisher ID is required", groups = {OnCreate.class, OnUpdate.class})
        Long publisherId,

        @NotNull(message = "Category ID is required", groups ={OnCreate.class, OnUpdate.class})
        Long categoryId,

        @NotEmpty(message = "At least one author is required", groups = {OnCreate.class, OnUpdate.class})
        List<Long> authorIds

) {}
