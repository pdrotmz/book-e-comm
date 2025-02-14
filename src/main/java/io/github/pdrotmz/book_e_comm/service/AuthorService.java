package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.model.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    Author registerAuthor(Author author);
    List<Author> findAllAuthors();
    Optional<Author> findAuthorById(UUID id);
    Optional<Author> findAuthorByName(String name);
    Optional<Author> updateAuthorById(UUID id, Author author);
    void deleteAuthorById(UUID id);
}
