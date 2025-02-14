package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.BookRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.BookResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    BookResponseDTO registerBook(BookRequestDTO request);
    List<Book> findAllBooks();
    Optional<Book> findBookById(UUID id);
    Optional<Book> findBookByName(String name);
    Optional<Book> updateBookById(UUID id, Book book);
    void deleteBookById(UUID id);
}
