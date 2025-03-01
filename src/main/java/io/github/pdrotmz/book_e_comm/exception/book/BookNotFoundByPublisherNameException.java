package io.github.pdrotmz.book_e_comm.exception.book;

public class BookNotFoundByPublisherNameException extends RuntimeException {
    public BookNotFoundByPublisherNameException(String publisherName) {
        super("Book with publisher name: " + publisherName + " not found!");
    }
}
