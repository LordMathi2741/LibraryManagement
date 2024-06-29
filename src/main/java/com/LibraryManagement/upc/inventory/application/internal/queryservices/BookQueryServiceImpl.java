package com.LibraryManagement.upc.inventory.application.internal.queryservices;

import com.LibraryManagement.upc.inventory.domain.model.aggregates.Book;
import com.LibraryManagement.upc.inventory.domain.model.queries.GetAllBooksQuery;
import com.LibraryManagement.upc.inventory.domain.services.BookQueryService;
import com.LibraryManagement.upc.inventory.infrastructure.persistence.jpa.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BookQueryServiceImpl
 * <p>
 *     - This class is used to implement the BookQueryService interface.
 *     - This class is used to handle the query GetAllBooksQuery.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class BookQueryServiceImpl implements BookQueryService {
    private final BookRepository bookRepository;
    public BookQueryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }
}
