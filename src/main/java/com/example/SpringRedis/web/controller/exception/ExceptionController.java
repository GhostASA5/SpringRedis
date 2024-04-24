package com.example.SpringRedis.web.controller.exception;

import com.example.SpringRedis.exception.EntityNotFoundException;
import com.example.SpringRedis.web.dto.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex) {
        log.error("Ошибка при попытке получить сущность", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getLocalizedMessage()));
    }
}
