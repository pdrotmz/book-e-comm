package io.github.pdrotmz.book_e_comm.exception.book;

public class BookNotFoundByAuthorNameException extends RuntimeException {
    public BookNotFoundByAuthorNameException(String authorName) {
        super("Book with author name: " + authorName + " not found!");
    }
}
