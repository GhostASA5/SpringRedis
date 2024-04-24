package com.example.SpringRedis.service;

import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.exception.EntityNotFoundException;
import com.example.SpringRedis.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Category with id " + id + " not found"));
    }

    public Category findByCategory(String category) {
        Category categoryFound = categoryRepository.findByCategory(category);
        if (categoryFound == null) {
            throw new EntityNotFoundException("Category with name " + category + " not found");
        }
        return categoryRepository.findByCategory(category);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
