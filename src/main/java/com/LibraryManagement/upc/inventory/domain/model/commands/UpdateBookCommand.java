package com.LibraryManagement.upc.inventory.domain.model.commands;

import java.util.Date;

/**
 * UpdateBookCommand
 * <p>
 *     - This class represents the UpdateBookCommand.
 *     - It contains the attributes of a book.
 * </p>
 * @param id
 * @param title
 * @param author
 * @param genreId
 * @param status
 * @param publicationDate
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public record UpdateBookCommand(Long id, String title, String author, Long genreId, Date publicationDate,String status) {
}
