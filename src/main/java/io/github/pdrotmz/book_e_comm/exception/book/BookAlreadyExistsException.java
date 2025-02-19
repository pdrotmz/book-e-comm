package io.github.pdrotmz.book_e_comm.exception.book;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
