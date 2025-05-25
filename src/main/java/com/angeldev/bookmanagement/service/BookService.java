package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> findAll();
    BookResponse findBook(Long id);
    BookResponse createBook(BookRequest bookRequest);
    BookResponse updateBook(Long id, BookRequest bookRequest);
    void deleteBook(Long id);
}
