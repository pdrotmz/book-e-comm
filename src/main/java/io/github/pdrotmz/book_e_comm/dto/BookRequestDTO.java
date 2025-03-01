package io.github.pdrotmz.book_e_comm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pdrotmz.book_e_comm.model.Publisher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRequestDTO(@NotNull(message = "ISBN cannot be empty") BigDecimal isbn,
                             @NotBlank(message = "Title cannot be empty") String name,
                             @NotNull(message = "Number of pages cannot be empty") Integer pageNumber,
                             @NotBlank(message = "Language cannot be empty") String language,
                             @NotNull(message = "Publication date cannot be empty") @JsonFormat(pattern = "dd-MM-yyyy") LocalDate publicationDate,
                             @NotNull(message = "Quantity cannot be null") Integer quantity,
                             @NotNull(message = "Price cannot be empty") BigDecimal price,
                             @NotNull(message = "Publisher's id cannot be empty") UUID publisherId,
                             @NotNull(message = "Author's id cannot be empty") UUID authorId) {
}
