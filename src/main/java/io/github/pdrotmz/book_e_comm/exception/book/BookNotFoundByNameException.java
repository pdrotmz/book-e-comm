package io.github.pdrotmz.book_e_comm.exception.book;

public class BookNotFoundByNameException extends RuntimeException {

    public BookNotFoundByNameException(String name) {
        super("Book with name: " + name + " not found!");
    }
}
