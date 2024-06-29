package com.LibraryManagement.upc.inventory.interfaces.rest.transform;

import com.LibraryManagement.upc.inventory.domain.model.commands.CreateBookCommand;
import com.LibraryManagement.upc.inventory.interfaces.rest.resources.CreateBookResource;

public class CreateBookCommandFromResourceAssembler {
    public static CreateBookCommand toCommandFromResource(CreateBookResource resource){
        return new CreateBookCommand(resource.isbn(), resource.title(), resource.author(), resource.genreId(),resource.publicationDate(), resource.status());
    }
}
