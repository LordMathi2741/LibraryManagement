package com.LibraryManagement.upc.inventory.application.internal.commandservices;

import com.LibraryManagement.upc.inventory.domain.model.aggregates.Book;
import com.LibraryManagement.upc.inventory.domain.model.commands.CreateBookCommand;
import com.LibraryManagement.upc.inventory.domain.model.commands.UpdateBookCommand;
import com.LibraryManagement.upc.inventory.domain.model.entities.Genre;
import com.LibraryManagement.upc.inventory.domain.model.exceptions.BookCannotPublishInTheFutureException;
import com.LibraryManagement.upc.inventory.domain.model.exceptions.InvalidStatusTypeException;
import com.LibraryManagement.upc.inventory.domain.services.BookCommandService;
import com.LibraryManagement.upc.inventory.infrastructure.persistence.jpa.repositories.BookRepository;
import com.LibraryManagement.upc.inventory.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 *  BookCommandServiceImpl class
 *  <p>
 *      - This class is used to implement the BookCommandService interface.
 *      - This class is used to handle the CreateBookCommand and UpdateBookCommand.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    public BookCommandServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Book> handle(CreateBookCommand command) {
        if (command.publicationDate().after(new Date())){
            throw new BookCannotPublishInTheFutureException();
        }
        if(!(command.status().equalsIgnoreCase("AVAILABLE") || command.status().equalsIgnoreCase("BORROWED"))){
            throw new InvalidStatusTypeException();
        }
        var genre = genreRepository.findById(command.genreId());
        var book = new Book(command,genre.orElseThrow());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> handle(UpdateBookCommand command) {
        if(command.publicationDate().after(new Date())){
            throw new BookCannotPublishInTheFutureException();
        }
        if(!(command.status().equalsIgnoreCase("AVAILABLE") || command.status().equalsIgnoreCase("BORROWED"))){
            throw new InvalidStatusTypeException();
        }
        var bookToUpDate = bookRepository.findById(command.id());
        if (bookToUpDate.isEmpty()){
            return Optional.empty();
        }
         bookToUpDate.get().setAuthor(command.author());
         bookToUpDate.get().setTitle(command.title());
         bookToUpDate.get().setStatus(command.status());
         bookToUpDate.get().setPublicationDate(command.publicationDate());
         bookToUpDate.get().setGenre(genreRepository.findById(command.genreId()).orElseThrow());
         bookRepository.save(bookToUpDate.get());
         return Optional.of(bookToUpDate.get());
    }
}
