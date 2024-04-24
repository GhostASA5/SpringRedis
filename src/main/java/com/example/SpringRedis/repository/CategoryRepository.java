package com.example.SpringRedis.repository;

import com.example.SpringRedis.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategory(String category);
}
