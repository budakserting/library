package com.asrihashim.library.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {

    @Id
    private String id;
    private String isbn;
    private String title;
    private String author;

}