package com.LibraryManagement.upc.inventory.domain.model.exceptions;

/**
 * BookCannotPublishInTheFutureException
 * <p>
 *     Exception thrown when a book is attempted to be created with a publication date in the future.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class BookCannotPublishInTheFutureException extends RuntimeException{
    public BookCannotPublishInTheFutureException(){
        super("Publication date cannot be in the future");
    }
}
