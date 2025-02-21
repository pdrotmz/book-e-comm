package io.github.pdrotmz.book_e_comm.service;

import io.github.pdrotmz.book_e_comm.dto.PublisherRequestDTO;
import io.github.pdrotmz.book_e_comm.dto.PublisherResponseDTO;
import io.github.pdrotmz.book_e_comm.exception.publisher.PublisherNotFoundByIdException;
import io.github.pdrotmz.book_e_comm.exception.publisher.PublisherNotFoundByNameException;
import io.github.pdrotmz.book_e_comm.model.Publisher;
import io.github.pdrotmz.book_e_comm.repository.PublisherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public PublisherResponseDTO registerPublisher(PublisherRequestDTO request) {
        Publisher publisher = new Publisher();

        publisher.setName(request.name());
        repository.save(publisher);


        return new PublisherResponseDTO(publisher.getId(), publisher.getName());
    }

    @Override
    public List<Publisher> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<Publisher> findPublisherById(UUID id) {
        if(repository.findById(id).isEmpty() || repository.findById(id) == null) {
            throw new PublisherNotFoundByIdException(id);
        }
        return repository.findById(id);
    }

    @Override
    public Optional<Publisher> findPublisherByName(String name) {
        if(repository.findPublisherByName(name).isEmpty() || repository.findPublisherByName(name) == null) {
            throw new PublisherNotFoundByNameException(name);
        }
        return repository.findPublisherByName(name);
    }

    @Override
    public Optional<Publisher> updatePublisherById(UUID id, Publisher publisher) {
        return repository.findById(id).map(existingPublisher -> {
            existingPublisher.setName(publisher.getName());

            return repository.save(existingPublisher);
        }).map(Optional::of).orElseThrow(() -> new PublisherNotFoundByIdException(id));
    }

    @Override
    public Optional<Publisher> updatePublisherByName(String name, Publisher publisher) {
        return repository.findPublisherByName(name).map(existingPublisher -> {
            existingPublisher.setName(publisher.getName());

            return repository.save(existingPublisher);
        }).map(Optional::of).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deletePublisherById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deletePublisherByName(String name) {
        repository.deletePublisherByName(name);
    }
}
