package com.example.SpringRedis.web.dto.book;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookListResponse {

    private List<BookResponse> books = new ArrayList<>();
}
