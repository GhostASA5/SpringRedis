package com.example.SpringRedis.mapper;

import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.web.dto.category.CategoryRequest;
import com.example.SpringRedis.web.dto.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BookMapper.class)
public interface CategoryMapper {

    Category requestToCategory(CategoryRequest request);

    CategoryResponse categoryToResponse(Category category);
}
