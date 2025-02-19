package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.BookRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.BookResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Book;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    BookResponseDTO registerBook(BookRequestDTO request);
    Page<BookResponseDTO> findAllBooks(int page, int size, String name, UUID authorId, BigDecimal minPrice, BigDecimal maxPrice);
    Optional<Book> findBookById(UUID id);
    Optional<Book> findBookByName(String name);
    Optional<Book> updateBookById(UUID id, Book book);
    void deleteBookById(UUID id);
}
