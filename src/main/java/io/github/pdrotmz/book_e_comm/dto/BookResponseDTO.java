package io.github.pdrotmz.book_e_comm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pdrotmz.book_e_comm.model.Author;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDTO(UUID id,
                              BigDecimal isbn,
                              String name,
                              Integer pageNUmber,
                              String language,
                              @JsonFormat(pattern = "dd-MM-yyyy")
                              LocalDate publicationDate,
                              Integer quantity,
                              BigDecimal price,
                              String authorName,
                              String publisherName) {
}
