package io.github.pdrotmz.book_e_comm.controller;

import io.github.pdrotmz.book_e_comm.dto.AuthorRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.AuthorResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Author;
import io.github.pdrotmz.book_e_comm.service.AuthorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("author")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthorResponseDTO> registerAuthor(@RequestBody AuthorRequestDTO request) {
        AuthorResponseDTO response = authorService.registerAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable UUID id) {
        Optional<Author> authorId = authorService.findAuthorById(id);
        return authorId
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<Author> findAUthorByName(@PathVariable String name) {
        Optional<Author> authorName = authorService.findAuthorByName(name);
        return authorName
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable UUID id, @RequestBody Author author) {
        Optional<Author> updatedAuthor = authorService.updateAuthorById(id, author);
        return updatedAuthor
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable UUID id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
