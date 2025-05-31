package com.example.Library_Management.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BorrowingRequest(
        @NotNull(message = "Member ID is required")
        Long memberId,
        @NotNull(message = "Book ID is required")
        Long bookId
) {
}
