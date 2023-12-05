package com.example.catalogservice.service.impl;

import com.example.catalogservice.entity.Book;
import com.example.catalogservice.exception.BookAlreadyExistsException;
import com.example.catalogservice.exception.BookNotFoundException;
import com.example.catalogservice.repository.BookRepository;
import com.example.catalogservice.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }
    public List<Book> viewBookList() {
        return bookRepository.findAll();
    }
    public Book viewBook(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if(optionalBook.isEmpty()) {
            throw new BookNotFoundException(isbn);
        }
        return optionalBook.get();
    }
    public Book addBookToCatalog(Book book) {
        String isbn = book.getIsbn();
        if(bookRepository.existsBookByIsbn(isbn)) {
            throw new BookAlreadyExistsException(isbn);
        }
        return bookRepository.save(book);
    }
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }
    public Book editBookDetails(Book book) {
        String isbn = book.getIsbn();
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if(optionalBook.isEmpty()) {
            bookRepository.save(book);
        }
        Book updateBook = optionalBook.get();
        modelMapper.map(book, updateBook);
        return bookRepository.save(updateBook);
    }
}
