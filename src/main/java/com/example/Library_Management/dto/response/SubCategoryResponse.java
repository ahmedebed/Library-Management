package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Category;
import lombok.Builder;

@Builder
public record SubCategoryResponse(
        Long id,
        String name
) {
    public static SubCategoryResponse mapToSubCategoryResponse(Category category) {
        return SubCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
