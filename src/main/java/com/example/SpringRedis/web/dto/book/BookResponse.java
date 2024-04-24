package com.example.SpringRedis.web.dto.book;

import lombok.Data;

@Data
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private String category;
}
