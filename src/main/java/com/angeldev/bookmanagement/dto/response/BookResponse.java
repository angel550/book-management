package com.angeldev.bookmanagement.dto.response;

import com.angeldev.bookmanagement.util.Status;

public record BookResponse(
        String title,
        String isbn,
        Integer publication_year,
        String author,
        String publisher,
        Status status
) {
}
