package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Author;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findAuthorByName(String name);

    boolean existsByNameAndDescription(@NotBlank String name, @NotBlank String description);
}
