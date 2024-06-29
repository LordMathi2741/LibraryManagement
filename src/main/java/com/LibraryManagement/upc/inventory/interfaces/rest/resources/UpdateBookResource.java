package com.LibraryManagement.upc.inventory.interfaces.rest.resources;

import java.util.Date;

public record UpdateBookResource(String title, String author, Long genreId, Date publicationDate, String status) {
}
