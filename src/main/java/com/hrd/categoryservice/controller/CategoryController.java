package com.hrd.categoryservice.controller;

import com.hrd.categoryservice.exception.BadRequestException;
import com.hrd.categoryservice.model.dto.request.CategoryRequest;
import com.hrd.categoryservice.model.dto.response.ApiResponse;
import com.hrd.categoryservice.model.dto.response.CategoryResponse;
import com.hrd.categoryservice.model.enumeration.CategoryProperty;
import com.hrd.categoryservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Category Controller")
//@SecurityRequirement(name = "bearerAuth")
public class CategoryController extends BaseController{
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories {paginated}")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam CategoryProperty categoryProperty,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection
    ) {
        try {
            if (page <= 0) throw new BadRequestException("Page index must not be less than zero");
            if (size <= 0) throw new BadRequestException("Page size must not be less than one");

            return null;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "Get all categorys {paginated}")
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable("categoryId") UUID categoryId) {
        try {
            return null;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> addCategory(@RequestBody CategoryRequest categoryRequest) {
        try {
            return null;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "Update category by id")
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable("categoryId") UUID categoryId, @RequestBody CategoryRequest categoryRequest) {
        try {
            return responseEntity("Update category successfully", categoryService.updateCategory(categoryId, categoryRequest));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "Delete category by id")
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
