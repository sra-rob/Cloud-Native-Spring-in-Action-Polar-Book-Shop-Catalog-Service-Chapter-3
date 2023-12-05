package com.example.catalogservice.entity.builders;

import com.example.catalogservice.entity.Book;

public class BookBuilder {
    private static BookBuilder bookBuilder;
    private static Book book;
    public static BookBuilder builder() {
        book = new Book();
        bookBuilder = new BookBuilder();
        return bookBuilder;
    }
    public BookBuilder setIsbn(String isbn) {
        book.setIsbn(isbn);
        return bookBuilder;
    }
    public BookBuilder setTitle(String title) {
        book.setTitle(title);
        return bookBuilder;
    }
    public BookBuilder setAuthor(String author) {
        book.setAuthor(author);
        return bookBuilder;
    }
    public BookBuilder setPrice(Double price) {
        book.setPrice(price);
        return bookBuilder;
    }
    public Book build() {
        return book;
    }

}
