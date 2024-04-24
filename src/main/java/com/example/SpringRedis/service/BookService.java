package com.example.SpringRedis.service;

import com.example.SpringRedis.domain.Book;
import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.exception.EntityNotFoundException;
import com.example.SpringRedis.repository.BookRepository;
import com.example.SpringRedis.repository.BookSpecification;
import com.example.SpringRedis.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Cacheable("bookByTitleAndAuthor")
    public Book findByTitleAndAuthor(String title, String author) {
        return bookRepository.findAll(BookSpecification.findByTitleAndAuthor(title, author)).get(0);
    }

    public List<Book> findByCategory(String category) {
        return bookRepository.findAll(BookSpecification.findByCategory(category));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        Book existingBook = findById(book.getId());
        Category excitedCategory = categoryService.findById(book.getCategory().getId());
        BeanUtils.copyNonNullProperties(book, existingBook);
        existingBook.setCategory(excitedCategory);
        return bookRepository.save(existingBook);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
