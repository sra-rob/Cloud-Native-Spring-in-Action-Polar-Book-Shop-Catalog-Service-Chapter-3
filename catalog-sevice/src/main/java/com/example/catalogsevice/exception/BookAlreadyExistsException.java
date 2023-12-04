package com.example.catalogsevice.exception;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String isbn) {
        super("Book with isbn %s already exists".formatted(isbn));
    }
}
