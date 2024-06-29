package com.LibraryManagement.upc.inventory.interfaces.rest.transform;

import com.LibraryManagement.upc.inventory.domain.model.aggregates.Book;
import com.LibraryManagement.upc.inventory.interfaces.rest.resources.BookResource;

public class BookResourceFromEntityAssembler {
    public static BookResource toResourceFromEntity(Book book){
        return new BookResource(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(),
                book.getGenre().getId(), book.getPublicationDate(), book.getStatus());
    }
}
