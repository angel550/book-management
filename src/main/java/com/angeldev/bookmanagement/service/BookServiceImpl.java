package com.angeldev.bookmanagement.service;

import com.angeldev.bookmanagement.dto.request.BookRequest;
import com.angeldev.bookmanagement.dto.response.BookResponse;
import com.angeldev.bookmanagement.exception.DuplicateObjectException;
import com.angeldev.bookmanagement.exception.ObjectNotFoundException;
import com.angeldev.bookmanagement.mappers.BookMapper;
import com.angeldev.bookmanagement.persistence.entity.Book;
import com.angeldev.bookmanagement.persistence.entity.Profile;
import com.angeldev.bookmanagement.persistence.repository.BookRepository;
import com.angeldev.bookmanagement.persistence.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BookResponse findBook(Long id) {
        Book book = bookRepository.findBookById(id).orElseThrow(() -> new ObjectNotFoundException(
                Book.class.getSimpleName(),
                id.toString()
        ));

        return BookMapper.bookToBookResponse(book);
    }

    @Transactional
    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Profile profile = this.getProfile(bookRequest.profileId());

        if (bookRepository.existsByIsbn(bookRequest.isbn())) {
            throw new DuplicateObjectException(Book.class.getSimpleName(), "isbn");
        }

        Book newBook = BookMapper.BookRequestToBook(bookRequest);

        newBook.setProfile(profile);

        return BookMapper.bookToBookResponse(bookRepository.save(newBook));
    }

    @Transactional
    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book oldBook = bookRepository.findBookById(id).orElseThrow(() -> new ObjectNotFoundException(
                Book.class.getSimpleName(),
                id.toString()
        ));

        Book newBook = BookMapper.BookRequestToBook(bookRequest);

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPublisher(newBook.getPublisher());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setPublicationYear(newBook.getPublicationYear());
        oldBook.setStatus(bookRequest.status());

        oldBook.setProfile(getProfile(bookRequest.profileId()));

        bookRepository.save(oldBook);

        return BookMapper.bookToBookResponse(oldBook);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBookById(id);
    }

    private Profile getProfile(Long id) {
        return profileRepository.findProfileById(id).orElseThrow(() -> new ObjectNotFoundException(
                Profile.class.getSimpleName(),
                id.toString()
        ));
    }
}
