package com.poc.bookingapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.bookingapi.model.Book;
import com.poc.bookingapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setBookName("Spring Boot in Action II");
        book.setPrice(46.99);
        book.setDescription("A comprehensive guide to Spring Boot");
        book.setAuthor("Craig Walls");
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/book/{bookId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1L))
                .andExpect(jsonPath("$.bookName").value("Spring Boot in Action II"));
    }

    @Test
    public void testSaveBook() throws Exception {
        when(bookService.saveBook(any(Book.class))).thenReturn("Book is saved successfully to db with ID: 62");

        mockMvc.perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().string("Book is saved successfully to db with ID: 62"));

    }

    @Test
    public void testUpdateBook() throws Exception {
        when(bookService.updateBook(any(Book.class), eq(1L))).thenReturn("Book is updated successfully with ID: 1");

        mockMvc.perform(put("/api/book/{bookId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().string("Book is updated successfully with ID: 1"));

    }

    @Test
    public void testDeleteBook() throws Exception {
        when(bookService.deleteBook(1L)).thenReturn("Delete book successfully with id: 1");

        mockMvc.perform(delete("/api/book/{bookId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete book successfully with id: 1"));

    }
}
