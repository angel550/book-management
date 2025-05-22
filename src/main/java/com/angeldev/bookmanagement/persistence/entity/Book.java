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

    public Book(String title, String isbn, Integer publication_year,
                String author, String publisher, Status status) {
        this.title = title;
        this.isbn = isbn;
        this.publication_year = publication_year;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String isbn;

    @Column(nullable = false)
    private Integer publication_year;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();*/
}
