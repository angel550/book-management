package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.mappers.BookMapper;
import com.angeldev.bookmanagement.persistence.entity.Book;
import com.angeldev.bookmanagement.persistence.entity.User;
import com.angeldev.bookmanagement.persistence.repository.BookRepository;
import com.angeldev.bookmanagement.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BookResponse> findAll() {
        List<Book> books = bookRepository.findAll();

        return BookMapper.bookToBookResponseList(books);
    }

    @Override
    public BookResponse findBook(String title) {
        Book book = bookRepository.findBookByTitle(title).get();

        return BookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Optional<User> user = userRepository.findUserByUsername(bookRequest.username());

        if (user.isPresent()) {
            System.out.println(user.get().getName());
        }

        Book newBook = BookMapper.BookRequestToBook(bookRequest);

        newBook.setUser(user.get());

        return BookMapper.bookToBookResponse(bookRepository.save(newBook));
    }

    @Override
    public BookResponse updateBook(String title, BookRequest bookRequest) {
        Book newBook = BookMapper.BookRequestToBook(bookRequest);

        Book oldBook = bookRepository.findBookByTitle(title).get();

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPublisher(newBook.getPublisher());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setPublication_year(newBook.getPublication_year());
        oldBook.setStatus(bookRequest.status());

        bookRepository.save(oldBook);

        return BookMapper.bookToBookResponse(oldBook);
    }

    @Override
    public void deleteBook(String title) {
        userRepository.deleteUserByUsername(title);
    }
}
