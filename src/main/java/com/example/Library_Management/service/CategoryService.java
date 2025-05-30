package com.example.Library_Management.service;

import com.example.Library_Management.dto.request.CategoryRequest;
import com.example.Library_Management.dto.response.CategoryResponse;
import com.example.Library_Management.entity.Category;
import com.example.Library_Management.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CategoryRequest request) {
        Category category = buildCategory(request);
        categoryRepository.save(category);
        return CategoryResponse.mapToCategoryResponse(category);
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryResponse.mapToDetailsCategoryResponse(category);
    }

    public Page<CategoryResponse> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(CategoryResponse::mapToCategoryResponse);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        updateCategory(request, category);
        categoryRepository.save(category);
        return CategoryResponse.mapToCategoryResponse(category);
    }

    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if ((category.getBooks() != null && !category.getBooks().isEmpty()) ||
                (category.getSubcategories() != null && !category.getSubcategories().isEmpty())) {
            throw new IllegalStateException("Cannot delete category with books or subcategories");
        }

        categoryRepository.deleteById(id);
    }

    private Category buildCategory(CategoryRequest request) {
        Category.CategoryBuilder builder = Category.builder()
                .name(request.name());

        if (request.parentCategoryId() != null) {
            Category parent = categoryRepository.findById(request.parentCategoryId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            builder.parentCategory(parent);
        }

        return builder.build();
    }

    private void updateCategory(CategoryRequest request, Category category) {
        if (request.name() != null)
            category.setName(request.name());

        if (request.parentCategoryId() != null) {
            Category parent = categoryRepository.findById(request.parentCategoryId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParentCategory(parent);
        } else {
            category.setParentCategory(null);
        }
    }
}
