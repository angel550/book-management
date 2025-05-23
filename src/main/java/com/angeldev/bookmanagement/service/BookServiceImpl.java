package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.mappers.BookMapper;
import com.angeldev.bookmanagement.persistence.entity.Book;
import com.angeldev.bookmanagement.persistence.entity.Profile;
import com.angeldev.bookmanagement.persistence.repository.BookRepository;
import com.angeldev.bookmanagement.persistence.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ProfileRepository profileRepository;

    public BookServiceImpl(BookRepository bookRepository, ProfileRepository profileRepository) {
        this.bookRepository = bookRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<BookResponse> findAll() {
        List<Book> books = bookRepository.findAll();

        return BookMapper.bookToBookResponseList(books);
    }

    @Override
    public List<BookResponse> findAll(String profile) {
        Long profileId = this.getProfile(profile).getId();

        List<Book> books = bookRepository.findAllFromProfile(profileId);

        return BookMapper.bookToBookResponseList(books);
    }

    @Override
    public BookResponse findBook(String title) {
        Book book = bookRepository.findBookByTitle(title).get();

        return BookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Profile profile = this.getProfile(bookRequest.profileName());

        Book newBook = BookMapper.BookRequestToBook(bookRequest);

        newBook.setProfile(profile);

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
        oldBook.setPublicationYear(newBook.getPublicationYear());
        oldBook.setStatus(bookRequest.status());

        bookRepository.save(oldBook);

        return BookMapper.bookToBookResponse(oldBook);
    }

    @Override
    public void deleteBook(String title) {
        bookRepository.deleteBookByTitle(title);
    }

    private Profile getProfile(String name) {
        return profileRepository.findProfileByName(name).get();
    }
}
