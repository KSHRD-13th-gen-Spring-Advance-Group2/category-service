package com.hrd.categoryservice.controller;

import com.hrd.categoryservice.exception.BadRequestException;
import com.hrd.categoryservice.model.dto.request.CategoryRequest;
import com.hrd.categoryservice.model.dto.response.ApiResponse;
import com.hrd.categoryservice.model.dto.response.CategoryResponse;
import com.hrd.categoryservice.model.dto.response.PagedResponse;
import com.hrd.categoryservice.model.enumeration.CategoryProperty;
import com.hrd.categoryservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Category Controller")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController extends BaseController{
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories (paginated)")
    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "CATEGORY_ID") CategoryProperty categoryProperty,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection
    ) {
        return responseEntity("Categories retrieved successfully", categoryService.getAllCategories(page, size, categoryProperty, sortDirection));
    }

    @Operation(summary = "Get category by ID")
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable("categoryId") UUID categoryId) {
        return responseEntity("Category retrieved successfully", categoryService.getCategoryById(categoryId));
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return responseEntity("Category created successfully", categoryService.createCategory(categoryRequest));
    }

    @Operation(summary = "Update category by ID")
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable("categoryId") UUID categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        try {
            return responseEntity("Update category successfully", categoryService.updateCategory(categoryId, categoryRequest));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "Delete category by ID")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteBookmark(@PathVariable("categoryId") UUID categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return responseEntity("Deleted category successfully", null);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
