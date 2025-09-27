package com.hrd.categoryservice.repository;

import com.hrd.categoryservice.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Page<Category> findAllByUserId(UUID userId, Pageable pageable);

    Optional<Category> findCategoriesByCategoryIdAndUserId(UUID categoryId, UUID userId);

    Boolean existsCategoryByNameAndUserId(String name, UUID userId);
}
