package com.LibraryManagement.upc.inventory.domain.services;

import com.LibraryManagement.upc.inventory.domain.model.aggregates.Book;
import com.LibraryManagement.upc.inventory.domain.model.commands.CreateBookCommand;
import com.LibraryManagement.upc.inventory.domain.model.commands.UpdateBookCommand;

import java.util.Optional;

/**
 * Book command service interface
 * <p>
 *     - Defines the methods that are used to handle the book commands
 *     - The methods are implemented in the BookCommandServiceImpl class
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface BookCommandService {

    Optional<Book> handle(CreateBookCommand command);
    Optional<Book> handle(UpdateBookCommand command);
}
