package com.poc.bookingapi.controller;

import com.poc.bookingapi.model.Book;
import com.poc.bookingapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {

    @Autowired
    private BookService bookService;  // field injection

    //get the book by id
    @Operation(
            summary = "Get a Book by ID",
            description = "Retrieve a book using its unique ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book found"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable final Long bookId) {
        return bookService.getBookById(bookId);
    }

    // create the book
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new book",
            description = "Save a new book to the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book created successfully")
            }
    )
    public String saveBook(@RequestBody  final Book book) {
        return bookService.saveBook(book);
    }

    // update the book by id
    @PutMapping(value = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update an existing book",
            description = "Update a book by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    public String updateBook(@RequestBody final Book book, @PathVariable  final Long bookId) {
        return bookService.updateBook(book, bookId);
    }

    // delete the book by id
    @DeleteMapping("/{bookId}")
    @Operation(
            summary = "Delete a book",
            description = "Delete a book by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    public String deleteBook(@PathVariable final Long bookId) {
        return bookService.deleteBook(bookId);
    }

}
