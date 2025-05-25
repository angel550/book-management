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
    Optional<Book> findBookById(Long id);
    void deleteBookById(Long id);
    boolean existsByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.profile.id = :profileId")
    List<Book> findAllFromProfile(@Param("profileId") Long profileId);
}
