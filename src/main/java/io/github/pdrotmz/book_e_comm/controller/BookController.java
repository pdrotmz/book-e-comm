package io.github.pdrotmz.book_e_comm.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pdrotmz.book_e_comm.dto.BookRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.BookResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Book;
import io.github.pdrotmz.book_e_comm.service.BookServiceImpl;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/register")
    public ResponseEntity<BookResponseDTO> registerBook(@Valid @RequestBody BookRequestDTO request) {
        BookResponseDTO response = bookService.registerBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/filter/")
    public ResponseEntity<Page<BookResponseDTO>> findAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) UUID authorId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
            ) {
        Page<BookResponseDTO> books = bookService.findAllBooks(page, size, name, authorId, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/search-by/id/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable UUID id) {
        Optional<Book> bookId = bookService.findBookById(id);
        return bookId.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search-by/name/{name}")
    public ResponseEntity<Book> findBookByName(@PathVariable String name) {
        Optional<Book> bookName = bookService.findBookByName(name);
        return bookName.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search-by/publicationDate/{publicationDate}")
    public ResponseEntity<List<Book>> findBookByPublicationDate(@PathVariable @JsonFormat(pattern = "dd-MM-yyyy") LocalDate publicationDate) {
        List<Book> books = bookService.findBookByPublicationDate(publicationDate);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/search-by/author/id/{authorId}")
    public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable UUID authorId) {
        List<Book> books = bookService.findBookByAuthorId(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/search-by/author/name/{authorName}")
    public ResponseEntity<List<Book>> findBookByAuthorName(@PathVariable String authorName) {
        List<Book> books = bookService.findBookByAuthorName(authorName);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("search-by/book/publisher/name/{publisherName}")
    public ResponseEntity<List<Book>> findBookByPublisherName(@PathVariable String publisherName) {
        List<Book> books = bookService.findBookByPublisherName(publisherName);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable UUID id, @RequestBody Book book) {
        Optional<Book> updatedBook = bookService.updateBookById(id, book);
        return updatedBook.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
