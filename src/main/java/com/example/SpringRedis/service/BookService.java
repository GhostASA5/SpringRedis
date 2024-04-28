package com.example.SpringRedis.service;

import com.example.SpringRedis.domain.Book;
import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.exception.EntityNotFoundException;
import com.example.SpringRedis.repository.BookRepository;
import com.example.SpringRedis.repository.BookSpecification;
import com.example.SpringRedis.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryService categoryService;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Cacheable(value = "bookByTitleAndAuthor", key = "#title + #author")
    public Book findByTitleAndAuthor(String title, String author) {
        try {
            return bookRepository.findAll(BookSpecification.findByTitleAndAuthor(title, author)).get(0);
        } catch (IndexOutOfBoundsException ex){
            throw new EntityNotFoundException(MessageFormat.format("Book with title {0} and author {1} not found.", title, author));
        }

    }

    @Cacheable(value = "booksByCategory", key = "#category")
    public List<Book> findByCategory(String category) {
        return bookRepository.findAll(BookSpecification.findByCategory(category));
    }

    @Caching(evict = {
            @CacheEvict(value = "bookByTitleAndAuthor", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "booksByCategory", allEntries = true, beforeInvocation = true)
    })
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Caching(evict = {
            @CacheEvict(value = "bookByTitleAndAuthor", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "booksByCategory", allEntries = true, beforeInvocation = true)
    })
    public Book update(Book book) {
        Book existingBook = findById(book.getId());
        Category excitedCategory = categoryService.findById(book.getCategory().getId());
        BeanUtils.copyNonNullProperties(book, existingBook);
        existingBook.setCategory(excitedCategory);
        return bookRepository.save(existingBook);
    }

    @Caching(evict = {
            @CacheEvict(value = "bookByTitleAndAuthor", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "booksByCategory", allEntries = true, beforeInvocation = true)
    })
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
