package com.example.SpringRedis.mapper.delegates;

import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.mapper.CategoryMapper;
import com.example.SpringRedis.web.dto.category.CategoryRequest;

public abstract class CategoryMapperDelegate implements CategoryMapper {

    @Override
    public Category requestToCategory(CategoryRequest request) {
        Category category = new Category();
        category.setCategory(request.getCategory());
        return category;
    }
}
