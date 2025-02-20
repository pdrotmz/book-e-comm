package io.github.pdrotmz.book_e_comm.controller;

import io.github.pdrotmz.book_e_comm.dto.PublisherRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.PublisherResponseDTO;
import io.github.pdrotmz.book_e_comm.model.Publisher;
import io.github.pdrotmz.book_e_comm.service.PublisherServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    private final PublisherServiceImpl publisherService;

    public PublisherController(PublisherServiceImpl publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/register")
    public ResponseEntity<PublisherResponseDTO> registerPublisher(@RequestBody PublisherRequestDTO request) {
        PublisherResponseDTO response = publisherService.registerPublisher(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> findAllBooks() {
        List<Publisher> publishers = publisherService.findAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(publishers);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<Publisher> findPublisherById(@PathVariable UUID id) {
        Optional<Publisher> publisherId = publisherService.findPublisherById(id);
        return publisherId
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<Publisher> findPublisherByName(@PathVariable String name) {
        Optional<Publisher> publisherName = publisherService.findPublisherByName(name);
        return publisherName
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Publisher> updatePublisherById(@PathVariable UUID id, @RequestBody Publisher publisher) {
        Optional<Publisher> updatedPublisher = publisherService.updatePublisherById(id, publisher);
        return updatedPublisher.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updated/name/{name}")
    public ResponseEntity<Publisher> updatePublisherByName(@PathVariable String name, @RequestBody Publisher publisher) {
        Optional<Publisher> updatedPublisher = publisherService.updatePublisherByName(name, publisher);
        return updatedPublisher.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> deletePublisherById(@PathVariable UUID id) {
        publisherService.deletePublisherById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<Void> deletePublisherByName(@PathVariable String name) {
        publisherService.deletePublisherByName(name);
        return ResponseEntity.noContent().build();
    }
}
