package com.hrd.categoryservice.model.enumeration;

import lombok.Getter;

@Getter
public enum CategoryProperty {
    CATEGORY_ID("categoryId"),
    CATEGORY_NAME("name");

    private final String fieldName;

    CategoryProperty(String fieldName) {
        this.fieldName = fieldName;
    }
}