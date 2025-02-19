package io.github.pdrotmz.book_e_comm.exception.book;

import java.util.UUID;

public class BookNotFoundByIdException extends RuntimeException {
    public BookNotFoundByIdException(UUID id) {
        super("Book with ID: " + id + " not found!");
    }
}
