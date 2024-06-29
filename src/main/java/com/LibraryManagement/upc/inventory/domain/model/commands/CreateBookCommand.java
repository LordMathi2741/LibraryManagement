package com.LibraryManagement.upc.inventory.domain.model.commands;

import java.util.Date;

/**
 * CreateBookCommand
 * <p>
 *     - This class represents the CreateBookCommand.
 *     - It contains the attributes of a book.
 * </p>
 * @param isbn
 * @param title
 * @param author
 * @param genreId
 * @param publicationDate
 * @param status
 * @author U20221721 Mathias Jave Diaz
 * @version 1.0
 */
public record CreateBookCommand(String isbn, String title, String author, Long genreId, Date publicationDate, String status) {
}
