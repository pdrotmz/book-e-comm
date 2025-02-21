package io.github.pdrotmz.book_e_comm.exception.publisher;

public class PublisherNotFoundByNameException extends RuntimeException {
    public PublisherNotFoundByNameException(String name) {
        super("Publisher with name " + name + " not found!");
    }
}
