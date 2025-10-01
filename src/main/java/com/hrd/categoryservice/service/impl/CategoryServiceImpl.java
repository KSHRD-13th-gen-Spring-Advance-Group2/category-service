package com.hrd.categoryservice.service.impl;

import com.hrd.categoryservice.client.UserClient;
import com.hrd.categoryservice.exception.DataConflictException;
import com.hrd.categoryservice.exception.NotFoundException;
import com.hrd.categoryservice.model.dto.request.CategoryRequest;
import com.hrd.categoryservice.model.dto.response.*;
import com.hrd.categoryservice.model.entity.Category;
import com.hrd.categoryservice.model.entity.User;
import com.hrd.categoryservice.model.enumeration.CategoryProperty;
import com.hrd.categoryservice.repository.CategoryRepository;
import com.hrd.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserClient userClient;

    private User getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new User(jwt);
    }

    @Override
    public PagedResponse<CategoryResponse> getAllCategories(Integer page, Integer size, CategoryProperty categoryProperty, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(direction, categoryProperty.getFieldName()));
//        Page<Category> categories = categoryRepository.findAllByUserId(getCurrentUser().getUserId(), pageable);

        // Mock UserResponse
        UserResponse userResponse = UserResponse.builder()
                .userId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .username("narin123")
                .email("narin@gmail.com")
                .firstName("horn")
                .lastName("narin")
                .imageUrl("image.jpg")
                .build();


        Page<com.hrd.categoryservice.model.entity.Category> categories = categoryRepository.findAllByUserId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), pageable);
        return PagedResponse.<CategoryResponse>builder()
                .items(categories.stream().map(category -> category.toResponse(userResponse)).toList())
                .pagination(new PaginationInfo(categories))
                .build();
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {

        User user = getCurrentUser();
        UserResponse userResponse = user.toResponse();

        return categoryRepository.findCategoriesByCategoryIdAndUserId(id, userResponse.getUserId()).orElseThrow(() ->
                new NotFoundException("Category with id of "+id+" not found")).toResponse(userResponse);
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        // Mock UserResponse
        UserResponse userResponse = UserResponse.builder()
                .userId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .username("narin123")
                .email("narin@gmail.com")
                .firstName("horn")
                .lastName("narin")
                .imageUrl("image.jpg")
                .build();

        if(categoryRepository.existsCategoryByNameAndUserId(categoryRequest.getName(), userResponse.getUserId()))
            throw new DataConflictException("Category already exists, Please choose a different category name");

        Category category = categoryRequest.toEntity();
        category.setUserId(userResponse.getUserId());

        return categoryRepository.save(category).toResponse(userResponse);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        // Mock UserResponse
        UserResponse userResponse = UserResponse.builder()
                .userId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .email("narin@gmail.com")
                .firstName("horn")
                .lastName("narin")
                .imageUrl("image.jpg")
                .build();

        return categoryRepository.save(category).toResponse(userResponse);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        categoryRepository.delete(category);
    }
}