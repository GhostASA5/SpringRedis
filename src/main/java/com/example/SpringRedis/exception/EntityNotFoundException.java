package com.example.SpringRedis.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message);
    }
}
