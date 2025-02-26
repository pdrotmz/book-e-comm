package io.github.pdrotmz.book_e_comm.dto;

import io.github.pdrotmz.book_e_comm.model.Book;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AuthorRequestDTO(
        @NotBlank String name,
        @NotBlank String description,
        List<Book> books) {
}
