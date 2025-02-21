package io.github.pdrotmz.book_e_comm.exception.publisher;

import java.util.UUID;

public class PublisherNotFoundByIdException extends RuntimeException {
    public PublisherNotFoundByIdException(UUID id) {
        super("Publisher with ID: " + id + " not found!");
    }
}
