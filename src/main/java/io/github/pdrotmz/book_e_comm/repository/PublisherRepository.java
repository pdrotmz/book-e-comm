package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    @Query("SELECT p FROM Publisher p WHERE p.name =:name")
    Optional<Publisher> findPublisherByName(@Param("name") String name);

    @Query("DELETE FROM Publisher p WHERE p.name =:name")
    void deletePublisherByName(@Param("name") String name);
}
