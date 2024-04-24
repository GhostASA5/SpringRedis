package com.example.SpringRedis.mapper.delegates;

import com.example.SpringRedis.domain.Book;
import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.mapper.BookMapper;
import com.example.SpringRedis.service.CategoryService;
import com.example.SpringRedis.web.dto.book.BookRequest;
import com.example.SpringRedis.web.dto.book.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BookMapperDelegate implements BookMapper {

    @Autowired
    private CategoryService service;

    @Override
    public Book requestToBook(BookRequest request) {
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        book.setCategory(service.findByCategory(request.getCategory()));
        return book;
    }

    @Override
    public Book requestToBook(Long id, BookRequest request) {
        Book book = requestToBook(request);
        book.setId(id);
        return book;
    }

    @Override
    public BookResponse bookToResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setAuthor(book.getAuthor());
        response.setTitle(book.getTitle());
        response.setCategory(book.getCategory().getCategory());
        return response;
    }
}
