package com.angeldev.bookmanagement.controller;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(@RequestParam(required = false) String profile) {
        if (profile == null) {
            return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(bookService.findAll(profile), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<BookResponse> findBook(@PathVariable String title) {
        return new ResponseEntity<>(bookService.findBook(title), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{title}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String title,
                                                   @Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.updateBook(title, bookRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{title}")
    public ResponseEntity<Void> deleteBook(@PathVariable String title) {
        bookService.deleteBook(title);
        return ResponseEntity.noContent().build();
    }
}
