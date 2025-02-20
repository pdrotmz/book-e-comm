package io.github.pdrotmz.book_e_comm.dto;

import io.github.pdrotmz.book_e_comm.model.Publisher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record BookRequestDTO(@NotBlank(message = "Title cannot be empty") String name,
                             @NotNull(message = "Quantity cannot be null") Integer quantity,
                             @NotNull(message = "Price cannot be empty") BigDecimal price,
                             @NotNull(message = "Publisher's id cannot be empty") UUID publisherId,
                             @NotNull(message = "Author's id cannot be empty") UUID authorId) {
}
