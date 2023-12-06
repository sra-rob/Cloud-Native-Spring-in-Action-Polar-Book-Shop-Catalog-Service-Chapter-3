package com.example.catalogservice.repository;

import com.example.catalogservice.entity.Book;
import com.example.catalogservice.entity.builders.BookBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;



@DataJpaTest
@DisplayName("BookRepository Tests")
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Test
    @DisplayName("save returns saved book")
    public void testSave_withValidArguments_returnsSavedBook() {
        Book book = BookBuilder.builder()
                .setIsbn("i-s-b-n")
                .setTitle("title1")
                .setAuthor("author1")
                .setPrice(12.12)
                .build();
        Book savedBook = bookRepository.save(book);
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());
    }
    @Test
    @DisplayName("findAll returns all books")
    public void testFindAll_returnsAllBooks() {
        Book book1 = BookBuilder.builder()
                .setIsbn("i-s-b-n-1")
                .setTitle("title1")
                .setAuthor("author1")
                .setPrice(11.11)
                .build();
        Book book2 = BookBuilder.builder()
                .setIsbn("i-s-b-n-2")
                .setTitle("title2")
                .setAuthor("author2")
                .setPrice(22.22)
                .build();
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> books = bookRepository.findAll();
        Assertions.assertThat(books.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("existsBookByIsbn returns true when book exists")
    public void testExistsBookByIsbn_whenBookExists_returnsTrue() {
        Book book = BookBuilder.builder()
                .setIsbn("i-s-b-n-1")
                .setTitle("title1")
                .setAuthor("author1")
                .setPrice(11.11)
                .build();
        bookRepository.save(book);
        boolean bookExists = bookRepository.existsBookByIsbn(book.getIsbn());
        Assertions.assertThat(bookExists).isTrue();
    }
    @Test
    @DisplayName("existsBookByIsbn returns false when book does not exists")
    public void testExistsBookByIsbn_whenBookDoesNotExist_returnsFalse() {
        Book book = BookBuilder.builder()
                .setIsbn("i-s-b-n-1")
                .setTitle("title1")
                .setAuthor("author1")
                .setPrice(11.11)
                .build();
        bookRepository.save(book);
        boolean bookExists = bookRepository.existsBookByIsbn("i-s-b-n-2");
        Assertions.assertThat(bookExists).isFalse();
    }
    @Test
    @DisplayName("findByIsbn returns non-empty Optional<Book> when book exists")
    public void findByIsbn_whenBookExists_returnsNonEmptyOptionalBook() {
        Book book = BookBuilder.builder()
                .setIsbn("i-s-b-n-1")
                .setTitle("title1")
                .setAuthor("author1")
                .setPrice(11.11)
                .build();
        bookRepository.save(book);
        Optional<Book> savedBook = bookRepository.findByIsbn(book.getIsbn());
        Book unwrappedBook = savedBook.get();
        Assertions.assertThat(unwrappedBook.getIsbn()).isEqualTo(book.getIsbn());
    }
    @Test
    @DisplayName("findByIsbn returns empty Optional<Book> when book does not exist")
    public void findByIsbn_whenBookDoesNotExists_returnsEmptyOptionalBook() {
        Optional<Book> savedBook = bookRepository.findByIsbn("i-s-b-n-1");
        Assertions.assertThat(savedBook.isEmpty()).isTrue();
    }
    @Test
    @DisplayName("deleteByIsbn deletes book")
    public void deleteByIsbn_whenBookExists_deletesBook() {
        Book book = BookBuilder.builder()
                .setIsbn("i-s-b-n-1")
                .setTitle("title1")
                .setAuthor("author1")
                .setPrice(11.11)
                .build();
        bookRepository.save(book);
        bookRepository.deleteByIsbn(book.getIsbn());
        Assertions.assertThat(bookRepository.existsBookByIsbn(book.getIsbn())).isFalse();
    }
}