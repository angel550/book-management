package com.angeldev.bookmanagement.dto.request;

import com.angeldev.bookmanagement.util.Status;
import jakarta.validation.constraints.*;

public record BookRequest(
        @NotBlank(message = "Title must not be blank")
        @Size(min = 2, max = 255, message = "Field must have between {min} and {max}")
        String title,

        @Size(min = 13, max = 13, message = "ISBN must be 13 digits")
        @Pattern(regexp = "\\d{13}", message = "ISBN must be numeric")
        String isbn,

        @Min(value = 1000, message = "Publication year must be equal or greater than 1000")
        @Max(value = 2025, message = "Publication year must be lower or equal than 2025")
        Integer publication_year,

        @NotBlank(message = "Author must not be blank")
        @Size(min = 2, max = 255, message = "Field must have between {min} and {max}")
        String author,

        @NotBlank(message = "Publisher must not be blank")
        @Size(min = 2, max = 255, message = "Publisher must be between {min} and {max} characters")
        String publisher,

        Status status,

        @Pattern(regexp = "[a-zA-Z0-9-_]{8,255}",
                message = "Username must consist of 8 to 255 characters and only contain letters, " +
                        "digits, and underscores.")
        String username
) {
}
