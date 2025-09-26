package com.hrd.categoryservice.model.entity;

import com.hrd.categoryservice.model.dto.response.CategoryResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id", updatable = false, nullable = false)
    private UUID categoryId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    public CategoryResponse toResponse() {
        return CategoryResponse.builder()
                .categoryId(this.categoryId)
                .name(this.name)
                .description(this.description)
                .userResponse(null)
                .build();
    }
}
