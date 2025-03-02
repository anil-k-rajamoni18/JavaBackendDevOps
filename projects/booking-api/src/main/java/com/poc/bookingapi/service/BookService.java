package com.poc.bookingapi.service;

import com.poc.bookingapi.exception.BookException;
import com.poc.bookingapi.model.Book;
import com.poc.bookingapi.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(final Long bookId) {
        // validate bookId
        if (bookId != null) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (bookOptional.isPresent()) {
                log.info("book found with id: {}", bookId);
                return bookOptional.get();
            }
        }
        log.info("book not found with id: {}", bookId);
        throw new BookException("Invalid book Id " + bookId, HttpStatus.BAD_REQUEST);
    }


    public String saveBook(final Book book) {
        if (book != null) {
            bookRepository.save(book);
            log.info("book saved to the database");
            return "Book is saved successfully to db with ID: " + book.getBookId();
        }
        throw new BookException("Invalid book request", HttpStatus.BAD_REQUEST);
    }

    public String updateBook(final Book book, final Long bookId) {
        if (bookId != null && book != null) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if(bookOptional.isPresent()) {
                Book dbbook = bookOptional.get();
                dbbook.setBookId(bookId);
                dbbook.setBookName(book.getBookName());
                dbbook.setPrice(book.getPrice());
                dbbook.setDescription(book.getDescription());
                dbbook.setAuthor(book.getAuthor());
                dbbook.setPublicationDate(book.getPublicationDate());
                bookRepository.save(dbbook);
                log.info("found book with id: {} and updated", bookId);
                return "Book is updated successfully with ID: " + dbbook.getBookId();
            }
        }
        log.info("book not found with id: {} to update", bookId);
        throw new BookException("Invalid book request", HttpStatus.BAD_REQUEST);
    }

    public String deleteBook(final Long bookId) {
        if (bookId != null) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (bookOptional.isPresent()) {
                bookRepository.deleteById(bookId);
                log.info("book found with id: {}, deleted", bookId);
                return "Delete book successfully with id: " + bookId;
            } else {
                throw new BookException("Couldn't find the book with id: " + bookId, HttpStatus.NOT_FOUND);
            }
        }
        throw new BookException("Invalid book Id " + bookId, HttpStatus.BAD_REQUEST);
    }
}
