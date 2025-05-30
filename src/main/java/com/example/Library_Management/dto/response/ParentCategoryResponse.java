package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Category;
import lombok.Builder;

@Builder
public record ParentCategoryResponse(
        Long id,
        String name
) {
    public static ParentCategoryResponse mapToParentCategoryResponse(Category category) {
        return ParentCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}