package com.example.SpringRedis.web.controller;

import com.example.SpringRedis.domain.Book;
import com.example.SpringRedis.mapper.BookMapper;
import com.example.SpringRedis.service.BookService;
import com.example.SpringRedis.web.dto.book.BookListResponse;
import com.example.SpringRedis.web.dto.book.BookRequest;
import com.example.SpringRedis.web.dto.book.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;


    @GetMapping("/{category}")
    public ResponseEntity<BookListResponse> getBooksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(bookMapper.bookListToResponseList(bookService.findByCategory(category)));
    }

    @GetMapping("/{title}/{author}")
    public ResponseEntity<BookResponse> findByTitleAndAuthor(@PathVariable String title,
                                                             @PathVariable String author) {
        return ResponseEntity.ok(bookMapper.bookToResponse(bookService.findByTitleAndAuthor(title, author)));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest bookRequest) {
        Book book = bookService.save(bookMapper.requestToBook(bookRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.bookToResponse(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        Book updatedBook = bookService.update(bookMapper.requestToBook(id, bookRequest));
        return ResponseEntity.ok(bookMapper.bookToResponse(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
