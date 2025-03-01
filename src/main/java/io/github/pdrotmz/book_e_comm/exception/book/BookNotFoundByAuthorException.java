package io.github.pdrotmz.book_e_comm.exception.book;

import java.util.UUID;

public class BookNotFoundByAuthorException extends RuntimeException {
    public BookNotFoundByAuthorException(UUID authorId) {
        super("Book with author ID: " + authorId + " not found!");
    }
}
