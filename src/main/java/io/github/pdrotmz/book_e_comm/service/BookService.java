package io.github.pdrotmz.book_e_comm.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pdrotmz.book_e_comm.dto.BookRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.BookResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Book;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    BookResponseDTO registerBook(BookRequestDTO request);
    Page<BookResponseDTO> findAllBooks(int page, int size, String name, UUID authorId, BigDecimal minPrice, BigDecimal maxPrice);
    List<Book> findAllBooks();
    List<Book> findBookByAuthorId(UUID authorId);
    List<Book> findBookByAuthorName(String authorName);
    List<Book> findBookByPublisherName(String publisherName);
    List<Book> findBookByPublicationDate(@JsonFormat(pattern = "dd-MM-yyy") LocalDate publicationDate);
    Optional<Book> findBookById(UUID id);
    Optional<Book> findBookByName(String name);
    Optional<Book> updateBookById(UUID id, Book book);
    void deleteBookById(UUID id);
}
