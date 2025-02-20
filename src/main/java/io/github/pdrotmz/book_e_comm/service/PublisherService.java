package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.PublisherRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.PublisherResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Publisher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublisherService {
    PublisherResponseDTO registerPublisher(PublisherRequestDTO request);
    List<Publisher> findAllBooks();
    Optional<Publisher> findPublisherById(UUID id);
    Optional<Publisher> findPublisherByName(String name);
    Optional<Publisher> updatePublisherById(UUID id, Publisher publisher);
    Optional<Publisher> updatePublisherByName(String name, Publisher publisher);
    void deletePublisherById(UUID id);
    void deletePublisherByName(String name);
}
