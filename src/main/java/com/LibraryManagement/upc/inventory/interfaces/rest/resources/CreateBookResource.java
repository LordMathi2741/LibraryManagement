package com.LibraryManagement.upc.inventory.interfaces.rest.resources;

import java.util.Date;

public record CreateBookResource(String isbn, String title, String author, Long genreId, Date publicationDate, String status) {
}
