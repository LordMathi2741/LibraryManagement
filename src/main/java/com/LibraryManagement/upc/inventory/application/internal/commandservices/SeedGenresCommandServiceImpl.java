package com.LibraryManagement.upc.inventory.application.internal.commandservices;

import com.LibraryManagement.upc.inventory.domain.model.commands.SeedGenresCommand;
import com.LibraryManagement.upc.inventory.domain.model.entities.Genre;
import com.LibraryManagement.upc.inventory.domain.model.valueobjects.GenreTypes;
import com.LibraryManagement.upc.inventory.domain.services.SeedGenresCommandService;
import com.LibraryManagement.upc.inventory.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * SeedGenresCommandServiceImpl
 * <p>
 *     - This class implements the SeedGenresCommandService interface.
 *     - This class is responsible for handling the SeedGenresCommand.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Service
public class SeedGenresCommandServiceImpl implements SeedGenresCommandService {
    private final GenreRepository genreRepository;
    public SeedGenresCommandServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    @Override
    public void handle(SeedGenresCommand command) {
        Arrays.stream(GenreTypes.values()).forEach(genreType -> {
            if (!genreRepository.existsByGenre(genreType)) {
                genreRepository.save(new Genre(genreType));
            }
        });
    }
}
