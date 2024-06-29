package com.LibraryManagement.upc.inventory.interfaces.rest.transform;

import com.LibraryManagement.upc.inventory.domain.model.commands.UpdateBookCommand;
import com.LibraryManagement.upc.inventory.interfaces.rest.resources.UpdateBookResource;

public class UpdateBookCommandFromResourceAssembler {

    public static UpdateBookCommand toCommandFromResource(Long id,UpdateBookResource resource){
        return new UpdateBookCommand(id,resource.title(),resource.author(),resource.genreId(),resource.publicationDate(), resource.status());
    }
}
