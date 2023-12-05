package com.example.catalogservice.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String isbn) {
        super("Book with isbn %s not found".formatted(isbn));
    }
}
