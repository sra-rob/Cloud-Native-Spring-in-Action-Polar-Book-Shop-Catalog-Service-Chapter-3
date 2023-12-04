package com.example.catalogsevice.entity.builders;

import com.example.catalogsevice.entity.Book;

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
    public BookBuilder setName(String name) {
        book.setName(name);
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
