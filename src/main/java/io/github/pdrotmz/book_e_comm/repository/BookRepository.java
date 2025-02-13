package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findBooksByName(String name);
}
