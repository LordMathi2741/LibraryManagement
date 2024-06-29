package com.LibraryManagement.upc.inventory.domain.model.exceptions;

/**
 *  InvalidStatusTypeException
 *  <p>
 *     - This class is used to handle the exception when the status of the book is not PUBLISHED or BORROWED.
 *     - This class extends RuntimeException.
 *  </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public class InvalidStatusTypeException extends RuntimeException {
    public InvalidStatusTypeException() {
        super("The status of the book must be PUBLISHED or BORROWED");
    }
}
