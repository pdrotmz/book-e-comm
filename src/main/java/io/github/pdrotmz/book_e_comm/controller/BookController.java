package io.github.pdrotmz.book_e_comm.controller;

import io.github.pdrotmz.book_e_comm.model.Book;
import io.github.pdrotmz.book_e_comm.service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/register")
    public ResponseEntity<Book> registerBook(@Valid @RequestBody Book book) {
        Book newBook = bookService.registerBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }
}
