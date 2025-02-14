package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.model.Author;
import io.github.pdrotmz.book_e_comm.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author registerAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(UUID id) {
        if(repository.findById(id) == null || repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Author was not found!");
        }
        return repository.findById(id);
    }

    @Override
    public Optional<Author> findAuthorByName(String name) {
        if(repository.findAuthorByName(name) == null || repository.findAuthorByName(name).isEmpty()) {
            throw new EntityNotFoundException("Book was not found!");
        }
        return repository.findAuthorByName(name);
    }

    @Override
    public Optional<Author> updateAuthorById(UUID id, Author author) {
        return repository.findById(id).map(existingAuthor -> {
            existingAuthor.setName(author.getName());
            existingAuthor.setDescription(author.getDescription());
            existingAuthor.setBooks(author.getBooks());

            return repository.save(existingAuthor);
        }).map(Optional::of).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteAuthorById(UUID id) {
        repository.deleteById(id);
    }
}
