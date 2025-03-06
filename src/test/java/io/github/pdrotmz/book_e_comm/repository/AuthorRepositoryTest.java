package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindAuthorByName() {
        Author author = new Author();
        author.setName("Graciliano Ramos");
        author.setDescription("Test Desc");

        entityManager.persist(author);
        entityManager.flush();

        Optional<Author> authorName = repository.findAuthorByName("Graciliano Ramos");
        assertThat(authorName).isPresent();
        assertThat(authorName.get().getName()).isEqualTo("Graciliano Ramos");
    }
}
