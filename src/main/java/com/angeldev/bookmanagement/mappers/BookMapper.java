package com.angeldev.bookmanagement.mappers;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.persistence.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static List<BookResponse> bookToBookResponseList(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book book: books) {
            bookResponses.add(bookToBookResponse(book));
        }

        return bookResponses;
    }

    public static BookResponse bookToBookResponse(Book book) {
        return new BookResponse(
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getAuthor(),
                book.getPublisher(),
                book.getStatus(),
                book.getProfile().getName()
        );
    }

    public static Book BookRequestToBook(BookRequest bookRequest) {
        return new Book(
                bookRequest.title(),
                bookRequest.isbn(),
                bookRequest.publicationYear(),
                bookRequest.author(),
                bookRequest.publisher(),
                bookRequest.status()
        );
    }
}
