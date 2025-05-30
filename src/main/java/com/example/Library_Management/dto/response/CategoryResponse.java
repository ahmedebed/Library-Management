package com.example.Library_Management.dto.response;

import com.example.Library_Management.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryResponse(
        Long id,
        String name,
        ParentCategoryResponse parentCategory,
        List<SubCategoryResponse> subcategories
) {
    public static CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategory(category.getParentCategory() != null
                        ? ParentCategoryResponse.mapToParentCategoryResponse(category.getParentCategory())
                        : null)
                .build();
    }
    public static CategoryResponse mapToDetailsCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategory(category.getParentCategory() != null
                        ? ParentCategoryResponse.mapToParentCategoryResponse(category.getParentCategory())
                        : null)
                .subcategories(category.getSubcategories() != null
                        ? category.getSubcategories().stream()
                        .map(SubCategoryResponse::mapToSubCategoryResponse)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }
}