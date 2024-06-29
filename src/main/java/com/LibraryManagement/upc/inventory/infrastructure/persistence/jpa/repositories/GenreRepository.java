package com.LibraryManagement.upc.inventory.infrastructure.persistence.jpa.repositories;

import com.LibraryManagement.upc.inventory.domain.model.entities.Genre;
import com.LibraryManagement.upc.inventory.domain.model.valueobjects.GenreTypes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Genre Repository
 * <p>
 *     - Genre Repository extends JpaRepository for Genre entity.
 *     - JpaRepository provides CRUD operations for Genre entity.
 *     - Follow open closed principle.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByGenre(GenreTypes genre);
}
