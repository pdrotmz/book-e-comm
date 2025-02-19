package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.AuthorRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.AuthorResponseDTO;
import io.github.pdrotmz.book_e_comm.exception.author.AuthorAlreadyExistsException;
import io.github.pdrotmz.book_e_comm.exception.author.AuthorNotFoundByIdException;
import io.github.pdrotmz.book_e_comm.exception.author.AuthorNotFoundByNameException;
import io.github.pdrotmz.book_e_comm.exception.author.InvalidAuthorDataException;
import io.github.pdrotmz.book_e_comm.model.Author;
import io.github.pdrotmz.book_e_comm.repository.AuthorRepository;
import org.springframework.stereotype.Service;

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
    public AuthorResponseDTO registerAuthor(AuthorRequestDTO request) {
        if(request.name().isBlank() || request.description().isBlank()) {
            throw new InvalidAuthorDataException("Invalid author data. Please check the fields.");
        }

        boolean authorExists = repository.existsByNameAndDescription(request.name(), request.description());

        if(authorExists) {
            throw new AuthorAlreadyExistsException("A author with this name and description already exists.");
        }

        Author author = new Author();
        author.setName(request.name());
        author.setDescription(request.description());
        author.setBooks(request.books());

        repository.save(author);
        return new AuthorResponseDTO(author.getId(), author.getName(), author.getDescription(), author.getBooks());
    }

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(UUID id) {
        if(repository.findById(id) == null || repository.findById(id).isEmpty()) {
            throw new AuthorNotFoundByIdException(id);
        }
        return repository.findById(id);
    }

    @Override
    public Optional<Author> findAuthorByName(String name) {
        if(repository.findAuthorByName(name) == null || repository.findAuthorByName(name).isEmpty()) {
            throw new AuthorNotFoundByNameException(name);
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
        }).map(Optional::of).orElseThrow(() -> new AuthorNotFoundByIdException(id));
    }

    @Override
    public void deleteAuthorById(UUID id) {
        repository.deleteById(id);
    }
}
