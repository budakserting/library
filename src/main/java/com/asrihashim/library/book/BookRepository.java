package com.asrihashim.library.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface BookRepository extends Repository<Book, String>{
    Book save(Book book);

    @Query("select b from Book b")
    List<Book> getAllBook();

    Optional<Book> findById(String id);
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);
}