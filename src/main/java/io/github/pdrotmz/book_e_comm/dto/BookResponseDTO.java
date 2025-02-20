package io.github.pdrotmz.book_e_comm.dto;

import io.github.pdrotmz.book_e_comm.model.Author;

import java.math.BigDecimal;
import java.util.UUID;

public record BookResponseDTO(UUID id, String name, Integer quantity, BigDecimal price, String authorName, String publisherName) {
}
