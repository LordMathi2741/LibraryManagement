package com.LibraryManagement.upc.inventory.domain.services;

import com.LibraryManagement.upc.inventory.domain.model.commands.SeedGenresCommand;

/**
 * SeedGenresCommandService
 * <p>
 *     Interface for the SeedGenresCommandService.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface SeedGenresCommandService {
    void handle(SeedGenresCommand command);
}
