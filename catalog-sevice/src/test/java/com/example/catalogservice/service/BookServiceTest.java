package com.example.catalogservice.service;

import com.example.catalogservice.entity.Book;
import com.example.catalogservice.entity.builders.BookBuilder;
import com.example.catalogservice.exception.BookNotFoundException;
import com.example.catalogservice.repository.BookRepository;
import com.example.catalogservice.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ModelMapper modelMapper;
    @Test
    @DisplayName("viewBookList returns all books")
    public void testViewBookList_returnsAllBooks() {
        Book book = mock(Book.class);
        when(bookRepository.findAll()).thenReturn(List.of(book, book));
        List<Book> books = bookServiceImpl.viewBookList();
        Assertions.assertThat(books.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("viewBook when book exists returns Book")
    public void testViewBook_whenBookExists_returnsBook() {
        Book book = mock(Book.class);
        when(book.getIsbn()).thenReturn("i-s-b-n-1");
        when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.of(book));
        Book savedBook = bookServiceImpl.viewBook(book.getIsbn());
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
    }
    @Test
    @DisplayName("viewBook when book does not exists throws BookNotFoundException")
    public void testViewBook_whenBookDoesNotExist_returnsBook() {
        when(bookRepository.findByIsbn("i-s-b-n-1")).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> {
            bookServiceImpl.viewBook("i-s-b-n-1");
        }).isInstanceOf(BookNotFoundException.class);
    }
    @Test
    @DisplayName("addBookToCatalog returns book when successfully added")
    public void testAddBook_whenArgumentsAreValid_returnsBook() {
        Book book = mock(Book.class);
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Book savedBook = bookServiceImpl.addBookToCatalog(book);
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
    }
    @Test
    @DisplayName("removeBookFromCatalog removes Book")
    public void testRemoveBookFromCatalog_whenArgumentsAreValid_returnsVoid() {
        assertAll(() -> bookServiceImpl.removeBookFromCatalog("i-s-b-n-1"));
    }
    @Test
    @DisplayName("editBookDetails returns book when successfully updated")
    public void testEditBookDetails_whenArgumentsAreValid_returnsBook() {
        Book book = Mockito.mock(Book.class);
        when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(Optional.of(book));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Book savedBook = bookServiceImpl.editBookDetails(book);
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
    }
}