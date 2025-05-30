package com.example.Library_Management.dto.request;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.Validation.OptionalNotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AuthorRequest(
        @NotBlank(message = "Author name must not be blank or empty",groups = {OnCreate.class})
        @OptionalNotBlank(groups = {OnUpdate.class})
        String name,
        String description,
        String nationality
) {
}
