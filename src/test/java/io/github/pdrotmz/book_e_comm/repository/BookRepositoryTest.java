package io.github.pdrotmz.book_e_comm.repository;

import io.github.pdrotmz.book_e_comm.model.Author;
import io.github.pdrotmz.book_e_comm.model.Book;
import io.github.pdrotmz.book_e_comm.model.Publisher;
import org.hibernate.engine.jdbc.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindBookByName() {
        Book book = new Book();
        book.setIsbn(BigDecimal.valueOf(156468459));
        book.setName("Test Book");
        book.setDescription("A Handbook of Agile Software Craftsmanship");
        book.setLanguage("English");
        book.setPageNumber(464);
        book.setPrice(BigDecimal.valueOf(45.99));
        book.setPublicationDate(LocalDate.of(2008, 8, 1));
        book.setQuantity(10);
        entityManager.persist(book);
        entityManager.flush();

        Optional<Book> bookName = repository.findBooksByName("Test Book");

        assertThat(bookName).isPresent();
        assertThat(bookName.get().getName()).isEqualTo("Test Book");
    }

    @Test
    void shouldFindBookByAuthorName() {
        Author author = new Author();
        author.setName("Graciliano Ramos");
        author.setDescription("Brazilian Author");
        entityManager.persist(author);
        entityManager.flush();

        Book book = new Book();
        book.setIsbn(BigDecimal.valueOf(1564684566));
        book.setName("Test Book");
        book.setDescription("A Handbook of Agile Software Craftsmanship");
        book.setLanguage("English");
        book.setPageNumber(464);
        book.setPrice(BigDecimal.valueOf(45.99));
        book.setPublicationDate(LocalDate.of(2008, 8, 1));
        book.setQuantity(10);
        book.setAuthor(author);
        entityManager.persist(book);
        entityManager.flush();

        List<Book> foundBooks = repository.findBookByAuthorName("Graciliano Ramos");

        assertThat(foundBooks).isNotNull();
        assertThat(foundBooks.get(0).getName()).isEqualTo("Test Book");
    }

    // TODO: findBookByAuthorId

    @Test
    void findBookByPublisherName() {
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        entityManager.persist(publisher);
        entityManager.flush();

        Book book = new Book();
        book.setIsbn(BigDecimal.valueOf(1564684566));
        book.setName("Test Book");
        book.setDescription("A Handbook of Agile Software Craftsmanship");
        book.setLanguage("English");
        book.setPageNumber(464);
        book.setPrice(BigDecimal.valueOf(45.99));
        book.setPublicationDate(LocalDate.of(2008, 8, 1));
        book.setQuantity(10);
        book.setPublisher(publisher);
        entityManager.persist(book);
        entityManager.flush();

        List<Book> foundBooks = repository.findBookByPublisherName("Test Publisher");
        assertThat(foundBooks).isNotNull();
        assertThat(foundBooks.get(0).getPublisher().getName());
    }


}
