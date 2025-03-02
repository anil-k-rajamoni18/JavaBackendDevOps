package com.poc.bookingapi;

import com.poc.bookingapi.model.Book;
import com.poc.bookingapi.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingApiApplicationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBookSavedByCommandLineRunner() {
        List<Book> books = bookRepository.findAll();

        assertNotNull(books);
        assertEquals(10, books.size());

        Book savedBook = books.get(0);
        assertEquals("Spring Boot in Action", savedBook.getBookName());
        assertEquals(45.99, savedBook.getPrice());
        assertEquals("A comprehensive guide to Spring Boot", savedBook.getDescription());
        assertEquals(LocalDate.of(2024, 1, 1), savedBook.getPublicationDate());
        assertEquals("Craig Walls", savedBook.getAuthor());
    }
}
