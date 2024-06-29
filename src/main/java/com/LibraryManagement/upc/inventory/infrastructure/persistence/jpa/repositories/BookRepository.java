package com.LibraryManagement.upc.inventory.infrastructure.persistence.jpa.repositories;

import com.LibraryManagement.upc.inventory.domain.model.aggregates.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Book Repository
 * <p>
 *     - This interface is used to provide the CRUD operations for the Book entity.
 *     - It extends the JpaRepository interface.
 *     - Follow open closed principle.
 * </p>
 * @author U202212721 Mathias Jave Diaz
 * @version 1.0
 */
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("SELECT b FROM Book b")
    List<Book> findAllBooks();
}
