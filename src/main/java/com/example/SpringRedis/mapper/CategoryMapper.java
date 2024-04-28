package com.example.SpringRedis.mapper;

import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.mapper.delegates.BookMapperDelegate;
import com.example.SpringRedis.mapper.delegates.CategoryMapperDelegate;
import com.example.SpringRedis.web.dto.category.CategoryRequest;
import com.example.SpringRedis.web.dto.category.CategoryResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(CategoryMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BookMapper.class)
public interface CategoryMapper {

    Category requestToCategory(CategoryRequest request);

    CategoryResponse categoryToResponse(Category category);
}
