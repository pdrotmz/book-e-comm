package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.BookRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.BookResponseDTO;
import io.github.pdrotmz.book_e_comm.exception.author.AuthorNotFoundByIdException;
import io.github.pdrotmz.book_e_comm.exception.book.*;
import io.github.pdrotmz.book_e_comm.model.Author;
import io.github.pdrotmz.book_e_comm.model.Book;
import io.github.pdrotmz.book_e_comm.model.Publisher;
import io.github.pdrotmz.book_e_comm.repository.AuthorRepository;
import io.github.pdrotmz.book_e_comm.repository.BookRepository;
import io.github.pdrotmz.book_e_comm.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository repository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public BookResponseDTO registerBook(BookRequestDTO request) {
        if(request.name().isBlank() || request.authorId() == null || request.quantity() == null || request.price() == null) {
            throw new InvalidBookDataException("Invalid book data. Please check the fields.");
        }

        if(request.price().intValue() <= 0) {
            throw new InvalidPriceException("Price must be great than and not equal to zero");
        }

        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new AuthorNotFoundByIdException(request.authorId()));

        Publisher publisher = publisherRepository.findById(request.publisherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean bookExists = repository.existsByNameIgnoreCaseAndAuthorId(request.name(), request.authorId());
        if(bookExists) {
            throw new BookAlreadyExistsException("A book with this name and author already exists.");
        }

        Book book = new Book();
        book.setIsbn(request.isbn());
        book.setName(request.name());
        book.setDescription(book.getDescription());
        book.setPageNumber(request.pageNumber());
        book.setLanguage(request.language());
        book.setPublicationDate(request.publicationDate());
        book.setQuantity(request.quantity());
        book.setPrice(request.price());

        book.setAuthor(author);
        book.setPublisher(publisher);

        repository.save(book);
        return new BookResponseDTO(
                book.getId(),
                book.getIsbn(),
                book.getName(),
                book.getPageNumber(),
                book.getLanguage(),
                book.getPublicationDate(),
                book.getQuantity(),
                book.getPrice(),
                book.getAuthor().getName(),
                book.getPublisher().getName());
    }

    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Page<BookResponseDTO> findAllBooks(int page, int size, String name, UUID authorId, BigDecimal minPrice, BigDecimal maxPrice) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<Book> books = repository.findAllWithFilters(name, authorId, minPrice, maxPrice, pageable);

        return books.map(book -> new BookResponseDTO(
                book.getId(),
                book.getIsbn(),
                book.getName(),
                book.getPageNumber(),
                book.getLanguage(),
                book.getPublicationDate(),
                book.getQuantity(),
                book.getPrice(),
                book.getAuthor().getName(),
                book.getPublisher().getName()
        ));
    }

    @Override
    public List<Book> findBookByAuthorId(UUID authorId) {
        if(repository.findBookByAuthorId(authorId).isEmpty() || repository.findBookByAuthorId(authorId) == null) {
            throw new BookNotFoundByAuthorException(authorId);
        }
        return repository.findBookByAuthorId(authorId);
    }

    @Override
    public List<Book> findBookByAuthorName(String authorName) {
        if(repository.findBookByAuthorName(authorName).isEmpty() ||
                repository.findBookByAuthorName(authorName) == null) {
            throw new BookNotFoundByAuthorNameException(authorName);
        }
        return repository.findBookByAuthorName(authorName);
    }

    @Override
    public List<Book> findBookByPublisherName(String publisherName) {
        if(repository.findBookByPublisherName(publisherName).isEmpty() ||
                repository.findBookByPublisherName(publisherName) == null) {
            throw new BookNotFoundByPublisherNameException(publisherName);
        }

        return repository.findBookByPublisherName(publisherName);
    }

    @Override
    public List<Book> findBookByPublicationDate(LocalDate publicationDate) {
        return repository.findBookRecentPublicationDate(publicationDate);
    }

    @Override
    public Optional<Book> findBookById(UUID id) {
        if(repository.findById(id) == null || repository.findById(id).isEmpty()) {
            throw new BookNotFoundByIdException(id);
        }
        return repository.findById(id);
    }

    @Override
    public Optional<Book> findBookByName(String name) {
        if(repository.findBooksByName(name) == null || repository.findBooksByName(name).isEmpty()) {
            throw new BookNotFoundByNameException(name);
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
        }).map(Optional::of).orElseThrow(() -> new BookNotFoundByIdException(id));
    }

    @Override
    public void deleteBookById(UUID id) {
        repository.deleteById(id);
    }
}
