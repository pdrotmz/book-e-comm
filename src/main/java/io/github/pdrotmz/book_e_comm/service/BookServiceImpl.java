package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.model.Book;
import io.github.pdrotmz.book_e_comm.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public Book registerBook(Book book) {
        return repository.save(book);
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
