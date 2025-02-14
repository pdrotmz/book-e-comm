package io.github.pdrotmz.book_e_comm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record BookRequestDTO(@NotBlank String name, @NotNull Integer quantity, @NotNull BigDecimal price, @NotNull UUID authorId) {
}
