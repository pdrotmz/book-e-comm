package io.github.pdrotmz.book_e_comm.exception;

import io.github.pdrotmz.book_e_comm.exception.author.AuthorNotFoundByIdException;
import io.github.pdrotmz.book_e_comm.exception.author.AuthorNotFoundByNameException;
import io.github.pdrotmz.book_e_comm.exception.author.InvalidAuthorDataException;
import io.github.pdrotmz.book_e_comm.exception.book.*;
import io.github.pdrotmz.book_e_comm.exception.publisher.InvalidPublisherDataException;
import io.github.pdrotmz.book_e_comm.exception.publisher.PublisherNotFoundByIdException;
import io.github.pdrotmz.book_e_comm.exception.publisher.PublisherNotFoundByNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundByIdException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundByIdException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundByNameException.class)
    public ResponseEntity<Object> handleBookNotFoundByNameException(BookNotFoundByNameException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundByAuthorException.class)
    public ResponseEntity<Object> handleBookNotFoundByAuthorException(BookNotFoundByAuthorException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundByAuthorNameException.class)
    public ResponseEntity<Object> handleBookNotFoundByAuthorNameException(BookNotFoundByAuthorNameException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundByPublisherNameException.class)
    public ResponseEntity<Object> handleBookNotFoundByPublisherNameException(BookNotFoundByPublisherNameException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorNotFoundByIdException.class)
    public ResponseEntity<Object> handleAuthorNotFoundByIdException(AuthorNotFoundByIdException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorNotFoundByNameException.class)
    public ResponseEntity<Object> handleAuthorNotFoundByNameException(AuthorNotFoundByNameException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("error", "Not Found");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBookDataException.class)
    public ResponseEntity<Object> handleInvalidBookDataException(InvalidBookDataException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", "Bad Request");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAuthorDataException.class)
    public ResponseEntity<Object> handleInvalidAuthorDataException(InvalidAuthorDataException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", "Bad Request");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<Object> handleBookAlreadyExistsException(BookAlreadyExistsException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT);
        body.put("error", "CONFLICT");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<Object> handleInvalidPriceException(InvalidPriceException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", "BAD REQUEST");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPublisherDataException.class)
    public ResponseEntity<Object> handleInvalidPublisherDataException(InvalidPublisherDataException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", "BAD REQUEST");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PublisherNotFoundByIdException.class)
    public ResponseEntity<Object> handlePublisherNotFoundByIdException(PublisherNotFoundByIdException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("error", "NOT FOUND");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublisherNotFoundByNameException.class)
    public ResponseEntity<Object> handlePublisherNotFoundByNameException(PublisherNotFoundByNameException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("error", "NOT FOUND");
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
