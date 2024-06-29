package com.LibraryManagement.upc.inventory.domain.model.entities;

import com.LibraryManagement.upc.inventory.domain.model.valueobjects.GenreTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * Genre entity
 * <p>
 *     - This class represents the Genre entity.
 *     - It contains the attributes of a genre.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
@Entity
@Getter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    @Size(max = 20)
    @NotBlank
    @Enumerated(EnumType.STRING)
    private GenreTypes genre;

    public Genre(){

    }


    public Genre(GenreTypes genre){
        this.genre = genre;
    }
}
