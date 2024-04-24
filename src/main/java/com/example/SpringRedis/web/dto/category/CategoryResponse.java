package com.example.SpringRedis.web.dto.category;

import com.example.SpringRedis.domain.Book;
import com.example.SpringRedis.web.dto.book.BookResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponse {

    private Long id;

    private String category;

    private List<BookResponse> books = new ArrayList<>();
}
