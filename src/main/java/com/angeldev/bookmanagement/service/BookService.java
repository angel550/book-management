package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> findAll();
    List<BookResponse> findAll(String profile);
    BookResponse findBook(String title);
    BookResponse createBook(BookRequest bookRequest);
    BookResponse updateBook(String title, BookRequest bookRequest);
    void deleteBook(String title);
}
