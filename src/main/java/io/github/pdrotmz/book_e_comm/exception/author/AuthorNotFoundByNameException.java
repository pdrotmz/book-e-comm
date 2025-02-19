package io.github.pdrotmz.book_e_comm.exception.author;

public class AuthorNotFoundByNameException extends RuntimeException {

    public AuthorNotFoundByNameException(String name) {
        super("Author with name: " + name + " not found!");
    }
}
