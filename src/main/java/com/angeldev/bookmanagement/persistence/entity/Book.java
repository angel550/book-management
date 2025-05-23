package com.angeldev.bookmanagement.persistence.entity;

import com.angeldev.bookmanagement.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {

    public Book(String title, String isbn, Integer publicationYear,
                String author, String publisher, Status status) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Profile profile;
}
