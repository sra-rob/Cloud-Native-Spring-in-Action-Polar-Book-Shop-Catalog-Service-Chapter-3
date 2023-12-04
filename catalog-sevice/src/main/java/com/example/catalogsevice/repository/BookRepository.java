package com.example.catalogsevice.repository;

import com.example.catalogsevice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    public Optional<Book> findByIsbn(String isbn);
    public boolean existsBookByIsbn(String isbn);
    public long deleteByIsbn(String isbn);
}
