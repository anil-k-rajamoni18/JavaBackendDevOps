package com.poc.bookingapi.service;



import com.poc.bookingapi.exception.BookException;
import com.poc.bookingapi.model.Book;
import com.poc.bookingapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setBookId(1L);
        book.setBookName("Spring Boot in Action");
        book.setPrice(45.99);
        book.setDescription("A comprehensive guide to Spring Boot");
        book.setAuthor("Craig Walls");
        book.setPublicationDate(LocalDate.now());
    }

    @Test
    public void testGetBookById_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getBookId());
        assertEquals("Spring Boot in Action", result.getBookName());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.getBookById(1L));

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveBook_Success() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        String result = bookService.saveBook(book);
        assertEquals("Book is saved successfully to db with ID: 1", result);

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        String result = bookService.updateBook(book, 1L);
        assertEquals("Book is updated successfully with ID: 1", result);

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.updateBook(book, 1L));

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        String result = bookService.deleteBook(1L);
        assertEquals("Delete book successfully with id: 1", result);

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBook_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookException.class, () -> bookService.deleteBook(1L));

        verify(bookRepository, times(1)).findById(1L);
    }
}
