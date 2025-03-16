package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindPublisherByName() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher Test");

        entityManager.persist(publisher);
        entityManager.flush();

        Optional<Publisher> publisherName = repository.findPublisherByName("Publisher Test");
        assertThat(publisherName).isPresent();
        assertThat(publisherName.get().getName()).isEqualTo("Publisher Test");
    }
}
