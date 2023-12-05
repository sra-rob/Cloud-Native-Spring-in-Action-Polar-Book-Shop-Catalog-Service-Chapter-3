package com.example.catalogservice.service;

import com.example.catalogservice.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public List<Book> viewBookList();
    public Book viewBook(String isbn);
    public Book addBookToCatalog(Book book);
    public void removeBookFromCatalog(String isbn);
    public Book editBookDetails(Book book);
}
