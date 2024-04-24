package com.example.SpringRedis.repository;

import com.example.SpringRedis.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecification {

    static Specification<Book> findByTitleAndAuthor(String title, String author) {
        return Specification.where(byTitle(title)).and(byAuthor(author));
    }

    static Specification<Book> byTitle(String title){
        return (root, query, criteriaBuilder) -> {
            if (title == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("title"), title);
        };
    }

    static Specification<Book> byAuthor(String author){
        return (root, query, criteriaBuilder) -> {
            if (author == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("author"), author);
        };
    }

    static Specification<Book> findByCategory(String category){
        return (root, query, criteriaBuilder) -> {
            if (category == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("category"), category);
        };
    }
}
