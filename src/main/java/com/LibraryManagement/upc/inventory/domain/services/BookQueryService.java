package com.LibraryManagement.upc.inventory.domain.services;

import com.LibraryManagement.upc.inventory.domain.model.aggregates.Book;
import com.LibraryManagement.upc.inventory.domain.model.queries.GetAllBooksQuery;

import java.util.List;

/**
 * Book Query Service
 * <p>
 *     - This interface provides methods to handle book queries.
 *     - The methods should be implemented by the corresponding service class.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface BookQueryService {
    List<Book> handle(GetAllBooksQuery query);
}
