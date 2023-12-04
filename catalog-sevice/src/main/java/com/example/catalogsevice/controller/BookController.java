package com.example.catalogsevice.controller;

import com.example.catalogsevice.entity.Book;
import com.example.catalogsevice.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }
    @GetMapping("{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.viewBook(isbn);
    }
    @PostMapping
    public Book post(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }
    @PutMapping
    public Book put(@RequestBody Book book) {
        return bookService.editBookDetails(book);
    }
    @DeleteMapping("{isbn}")
    public void delete(String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }
}
