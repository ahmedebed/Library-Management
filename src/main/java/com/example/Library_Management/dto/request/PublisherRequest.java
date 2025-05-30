package com.example.Library_Management.dto.request;

import com.example.Library_Management.Validation.OnCreate;
import com.example.Library_Management.Validation.OnUpdate;
import com.example.Library_Management.Validation.OptionalNotBlank;
import com.example.Library_Management.Validation.UniquePublisherName;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PublisherRequest(
        @NotBlank(message = "Publisher name must not be blank", groups = {OnCreate.class})
        @UniquePublisherName(groups = OnCreate.class)
        @OptionalNotBlank(groups = {OnUpdate.class})
        String name,
        String address
) {}
