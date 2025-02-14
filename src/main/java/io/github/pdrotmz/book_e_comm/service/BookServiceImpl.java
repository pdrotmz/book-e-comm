package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.BookRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.BookResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Author;
import io.github.pdrotmz.book_e_comm.model.Book;
import io.github.pdrotmz.book_e_comm.repository.AuthorRepository;
import io.github.pdrotmz.book_e_comm.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }


    @Override
    public BookResponseDTO registerBook(BookRequestDTO request) {

        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Book book = new Book();
        book.setName(request.name());
        book.setDescription(book.getDescription());
        book.setQuantity(request.quantity());
        book.setPrice(request.price());
        book.setAuthor(author);

        repository.save(book);
        return new BookResponseDTO(book.getId() ,book.getName(), book.getQuantity(), book.getPrice(), book.getAuthor().getName());
    }

    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findBookById(UUID id) {
        if(repository.findById(id) == null || repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Book was not found!");
        }
        return repository.findById(id);
    }

    @Override
    public Optional<Book> findBookByName(String name) {
        if(repository.findBooksByName(name) == null || repository.findBooksByName(name).isEmpty()) {
            throw new EntityNotFoundException("Book was not found!");
        }
        return repository.findBooksByName(name);
    }

    @Override
    public Optional<Book> updateBookById(UUID id, Book book) {
        return repository.findById(id).map(existingBook -> {
            existingBook.setName(book.getName());
            existingBook.setDescription(book.getDescription());
            existingBook.setQuantity(book.getQuantity());
            existingBook.setPrice(book.getPrice());

            return repository.save(existingBook);
        })
                .map(Optional::of)
                .orElseThrow(() -> new RuntimeException("Error while saving!"));
    }

    @Override
    public void deleteBookById(UUID id) {
        repository.deleteById(id);
    }
}
