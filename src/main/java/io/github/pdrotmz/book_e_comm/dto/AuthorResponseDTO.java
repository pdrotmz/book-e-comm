package io.github.pdrotmz.book_e_comm.dto;

import io.github.pdrotmz.book_e_comm.model.Book;

import java.util.List;
import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name,
        String description,
        List<Book> books) {
}
