package com.LibraryManagement.upc.inventory.domain.model.aggregates;


import com.LibraryManagement.upc.inventory.domain.model.commands.CreateBookCommand;
import com.LibraryManagement.upc.inventory.domain.model.entities.Genre;
import com.LibraryManagement.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Book aggregate root
 * <p>
 *     - This class represents the Book aggregate root.
 *     - It contains the attributes of a book.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */

@Entity
@Getter
public class Book extends AuditableAbstractAggregateRoot<Book> {

    @Column(unique = true,length = 13)
    @Size(max=13)
    @NotBlank
    private String isbn;

    @Column(length = 100)
    @Size(max=100)
    @NotBlank
    @Setter
    private String title;

    @Column(length = 50)
    @Size(max=50)
    @NotBlank
    @Setter
    private String author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    @Setter
    private Genre genre;

    @Setter
    @Column(nullable = false)
    private Date publicationDate;

    @Column(length = 10)
    @Size(max=10)
    @NotBlank
    @Setter
    private String status;

    public Book(){

    }

    public Book(CreateBookCommand command, Genre genre){
        this.isbn = command.isbn();
        this.title = command.title();
        this.author = command.author();
        this.genre = genre;
        this.publicationDate = command.publicationDate();
        this.status = command.status();
    }


}
