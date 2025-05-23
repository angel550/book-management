package com.angeldev.bookmanagement.dto.response;

import com.angeldev.bookmanagement.persistence.entity.Profile;
import com.angeldev.bookmanagement.util.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookResponse(
        String title,

        String isbn,

        @JsonProperty(value = "publication_year")
        Integer publicationYear,

        String author,

        String publisher,

        Status status,

        @JsonProperty(value = "profile_name")
        String profileName
) {
}
