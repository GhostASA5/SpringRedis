package com.example.SpringRedis.mapper;

import com.example.SpringRedis.domain.Book;
import com.example.SpringRedis.domain.Category;
import com.example.SpringRedis.mapper.delegates.BookMapperDelegate;
import com.example.SpringRedis.web.dto.book.BookListResponse;
import com.example.SpringRedis.web.dto.book.BookRequest;
import com.example.SpringRedis.web.dto.book.BookResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(BookMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book requestToBook(BookRequest request);

    @Mapping(source = "bookId", target = "id")
    Book requestToBook(Long bookId, BookRequest request);

    BookResponse bookToResponse(Book book);

    default BookListResponse bookListToResponseList(List<Book> books) {
        BookListResponse response = new BookListResponse();
        response.setBooks(books.stream().map(this::bookToResponse).collect(Collectors.toList()));
        return response;
    }

    Category map(String value);

}
