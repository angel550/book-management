package com.angeldev.bookmanagement.controller;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book REST API Endpoints", description = "Operations for managing books")
@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books for a profile", description = "Fetch all books from a profile")
    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(@RequestParam(required = false) String profile) {
        if (profile == null) {
            return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(bookService.findAll(profile), HttpStatus.OK);
    }

    @Operation(summary = "Get a book by title", description = "Retrieve a specific book by title")
    @GetMapping("/{title}")
    public ResponseEntity<BookResponse> findBook(@PathVariable String title) {
        return new ResponseEntity<>(bookService.findBook(title), HttpStatus.OK);
    }

    @Operation(summary = "Create a new book for a profile", description = "Add a new book for a profile")
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a book for a profile", description = "Update book for a specific profile")
    @PutMapping(value = "/{title}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String title,
                                                   @Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.updateBook(title, bookRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a book for a profile", description = "Delete book for a specific profile")
    @DeleteMapping(value = "/{title}")
    public ResponseEntity<Void> deleteBook(@PathVariable String title) {
        bookService.deleteBook(title);
        return ResponseEntity.noContent().build();
    }
}
