package com.LibraryManagement.upc.inventory.interfaces.rest;

import com.LibraryManagement.upc.inventory.domain.model.queries.GetAllBooksQuery;
import com.LibraryManagement.upc.inventory.domain.services.BookCommandService;
import com.LibraryManagement.upc.inventory.domain.services.BookQueryService;
import com.LibraryManagement.upc.inventory.interfaces.rest.resources.BookResource;
import com.LibraryManagement.upc.inventory.interfaces.rest.resources.CreateBookResource;
import com.LibraryManagement.upc.inventory.interfaces.rest.resources.UpdateBookResource;
import com.LibraryManagement.upc.inventory.interfaces.rest.transform.BookResourceFromEntityAssembler;
import com.LibraryManagement.upc.inventory.interfaces.rest.transform.CreateBookCommandFromResourceAssembler;
import com.LibraryManagement.upc.inventory.interfaces.rest.transform.UpdateBookCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *  BookController class
 *  <p>
 *      - This class is a Rest Controller that handles the requests related to the Book entity.
 *      - It has the following methods:
 *      - createBook: It creates a new book.
 *      - updateBook: It updates an existing book.
 *      - getAllBooks: It returns all the books.
 *  </p>
 * @author  U202212721 Mathias Jave Diaz
 * @version 1.0
 *
 */

@RestController
@RequestMapping(value ="/api/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "201", description = "Book created"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
@Tag(name = "Book Controller", description = "The Book Controller Endpoints")
public class BookController {

    private final BookCommandService bookCommandService;
    private final BookQueryService bookQueryService;
    public BookController(BookCommandService bookCommandService, BookQueryService bookQueryService) {
        this.bookCommandService = bookCommandService;
        this.bookQueryService = bookQueryService;
    }

    @PostMapping
    @PreAuthorize(" hasAuthority('LIBRARIAN')")
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResource> createBook(@RequestBody CreateBookResource createBookResource, @RequestHeader("Authorization") String token){
        var command = CreateBookCommandFromResourceAssembler.toCommandFromResource(createBookResource);
        var book = bookCommandService.handle(command);
        var bookResource = BookResourceFromEntityAssembler.toResourceFromEntity(book.get());
        return new ResponseEntity<>(bookResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book")
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public ResponseEntity<BookResource> updateBook(@RequestParam Long id,@RequestBody UpdateBookResource updateBookResource, @RequestHeader("Authorization") String token){
        var command = UpdateBookCommandFromResourceAssembler.toCommandFromResource(id,updateBookResource);
        var book = bookCommandService.handle(command);
        var bookResource = BookResourceFromEntityAssembler.toResourceFromEntity(book.get());
        return new ResponseEntity<>(bookResource, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER') or hasAuthority('LIBRARIAN')")
    public ResponseEntity<BookResource> getAllBooks(@RequestHeader("Authorization") String token){
        var query = new GetAllBooksQuery();
        var books = bookQueryService.handle(query);
        return books.stream().map(book -> new ResponseEntity<>(BookResourceFromEntityAssembler.toResourceFromEntity(book), HttpStatus.OK)).findAny().orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
