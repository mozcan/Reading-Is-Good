package com.mozcan.readingIsGood.controller;

import com.mozcan.readingIsGood.controller.dto.BookCreateRequest;
import com.mozcan.readingIsGood.controller.dto.BookResponse;
import com.mozcan.readingIsGood.controller.dto.BookUpdateRequest;
import com.mozcan.readingIsGood.controller.dto.ErrorResponse;
import com.mozcan.readingIsGood.exception.BookNotFoundException;
import com.mozcan.readingIsGood.exception.OrderNotFoundException;
import com.mozcan.readingIsGood.service.BookService;
import com.mozcan.readingIsGood.utils.ModelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/book")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBook(@RequestBody @Valid BookCreateRequest bookCreateRequest) {

        if (bookCreateRequest.getStock() <= 0) {
            var message = "Stock must be more than zero";
            logger.error(message);

            return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (bookCreateRequest.getPrice() <= 0) {
            var message = "Price must be more than zero";
            logger.error(message);

            return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var book = bookService.create(bookCreateRequest);

        var modelConverter = new ModelConverter();
        var response = modelConverter.bookEntityToBookResponse(book);

        logger.info("Book has been added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookUpdateRequest updateRequest) {
        try {
            var book = bookService.update(updateRequest);
            var modelConverter = new ModelConverter();

            var response = modelConverter.bookEntityToBookResponse(book);

            logger.info("Book has been updated successfully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BookNotFoundException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        try {
            var book = bookService.getBook(id);

            var modelConverter = new ModelConverter();
            var response = modelConverter.bookEntityToBookResponse(book);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BookNotFoundException ex) {
            logger.error(ex.getMessage());

            var errorResponse = new ErrorResponse(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}