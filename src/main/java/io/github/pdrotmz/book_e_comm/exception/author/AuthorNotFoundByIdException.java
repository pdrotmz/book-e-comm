package io.github.pdrotmz.book_e_comm.exception.author;

import java.util.UUID;

public class AuthorNotFoundByIdException extends RuntimeException {
    public AuthorNotFoundByIdException(UUID id) {
        super("Author with ID: " + id + " not found!");
    }
}
