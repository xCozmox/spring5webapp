package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven bla bla", "123123");
        Publisher bobPublisher = new Publisher("Bob Publisher House", "YorkStreet 20", "Chicago", "Rheinland-Pfalz", "54343");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        bobPublisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "39393");

        bobPublisher.getBooks().add(noEJB);


        authorRepository.save(rod);
        bookRepository.save(noEJB);

        publisherRepository.save(bobPublisher);
        log.info("Number of Publishers: " + publisherRepository.count());
        log.info("Number of Books: " + bookRepository.count());
        log.info("Number of Books of Publisher: " + bobPublisher.getBooks().size());
    }
}
