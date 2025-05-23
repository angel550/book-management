package com.angeldev.bookmanagement.persistence.repository;

import com.angeldev.bookmanagement.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle(String title);
    void deleteBookByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.profile.id = :profileId")
    List<Book> findAllFromProfile(@Param("profileId") Long profileId);
}
