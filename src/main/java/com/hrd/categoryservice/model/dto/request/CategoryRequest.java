package com.hrd.categoryservice.model.dto.request;

import com.hrd.categoryservice.model.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    @NotBlank(message = "Category name is required")
    @Size(min = 3, message = "Category name must be at least 3 characters")
    @Size(max = 255, message = "Category name must not exceed 255 characters")
    @Pattern(
            regexp = "^[a-zA-Z][\\da-zA-Z\\s]*$",
            message = "Category name must start with letter"
    )
    private String name;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    public Category toEntity() {
        return Category.builder()
                .categoryId(null)
                .name(this.name)
                .description(this.description)
                .userId(null)
                .build();
    }
}