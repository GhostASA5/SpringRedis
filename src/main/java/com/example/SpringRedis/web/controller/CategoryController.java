package com.example.SpringRedis.web.controller;

import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.mapper.CategoryMapper;
import com.example.SpringRedis.service.CategoryService;
import com.example.SpringRedis.web.dto.category.CategoryRequest;
import com.example.SpringRedis.web.dto.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.save(categoryMapper.requestToCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.categoryToResponse(category));
    }
}
