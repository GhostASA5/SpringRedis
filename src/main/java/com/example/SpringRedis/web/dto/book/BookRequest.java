package com.example.SpringRedis.web.dto.book;

import lombok.Data;

@Data
public class BookRequest {

    private String title;

    private String author;

    private String category;
}
