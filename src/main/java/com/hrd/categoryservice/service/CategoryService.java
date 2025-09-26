package com.hrd.categoryservice.service;

import com.hrd.categoryservice.model.dto.request.CategoryRequest;
import com.hrd.categoryservice.model.dto.response.PagedResponse;
import com.hrd.categoryservice.model.dto.response.CategoryResponse;
import com.hrd.categoryservice.model.enumeration.CategoryProperty;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface CategoryService {
    PagedResponse<CategoryResponse> getAllCategories(Integer page, Integer size, CategoryProperty categoryProperty, Sort.Direction direction);

    CategoryResponse getCategoryById(UUID id);

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest);

    void deleteCategory(UUID id);
}
