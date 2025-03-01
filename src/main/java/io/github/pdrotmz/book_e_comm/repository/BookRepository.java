package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("SELECT b FROM Book b WHERE b.name =:name")
    Optional<Book> findBooksByName(@Param("name") String name);

    @Query(value = """
        SELECT b FROM Book b
        WHERE (:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:authorId IS NULL OR b.author.id = :authorId)
        AND (:minPrice IS NULL OR b.price >= :minPrice)
        AND (:maxPrice IS NULL OR b.price <= :maxPrice)
    """)
     Page<Book> findAllWithFilters(
            @Param("name") String name,
            @Param("authorId") UUID authorId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
            );

    boolean existsByNameIgnoreCaseAndAuthorId(@NotBlank(message = "Title cannot be empty") String name, @NotNull(message = "Author's id cannot be empty") UUID authorId);

    @Query("SELECT b FROM Book b WHERE b.publicationDate >= :publicationDate ORDER BY b.publicationDate ASC")
    List<Book> findBookRecentPublicationDate(@Param("publicationDate") LocalDate publicationDate);

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.author.id =:authorId")
    List<Book> findBookByAuthorId(@Param("authorId") UUID authorId);

    @Query("SELECT b FROM Book b JOIN FETCH b.publisher WHERE b.publisher.name =:publisherName")
    List<Book> findBookByPublisherName(@Param("publisherName") String publisherName);

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.author.name =:authorName")
    List<Book> findBookByAuthorName(@Param("authorName") String authorName);
}
