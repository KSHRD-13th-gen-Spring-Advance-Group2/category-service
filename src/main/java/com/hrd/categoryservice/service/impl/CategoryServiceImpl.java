package com.hrd.categoryservice.service.impl;

import com.hrd.categoryservice.exception.NotFoundException;
import com.hrd.categoryservice.model.dto.request.CategoryRequest;
import com.hrd.categoryservice.model.dto.response.PagedResponse;
import com.hrd.categoryservice.model.dto.response.CategoryResponse;
import com.hrd.categoryservice.model.entity.Category;
import com.hrd.categoryservice.model.enumeration.CategoryProperty;
import com.hrd.categoryservice.repository.CategoryRepository;
import com.hrd.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public PagedResponse<CategoryResponse> getAllCategories(Integer page, Integer size, CategoryProperty categoryProperty, Sort.Direction direction) {
        return null;
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setUserId(categoryRequest.getUserId());

        return categoryRepository.save(category).toResponse();
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        categoryRepository.delete(category);
    }
}