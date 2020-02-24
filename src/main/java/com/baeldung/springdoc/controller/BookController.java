package com.baeldung.springdoc.controller;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.baeldung.springdoc.exception.BookNotFoundException;
import com.baeldung.springdoc.model.Book;
import com.baeldung.springdoc.repository.BookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookRepository repository;

	@Operation(parameters = {
			@Parameter(name = "id", example = "1", description = "Es la id por la cual se busca el libro"),
			@Parameter(name = "nombre", required = true, content = @Content(mediaType = "String"), example = "Omar", description = "asdsad") }, tags = "Metodos Get", description = "Esta api consulta la base de datos con un id entregado y trae el libro correspondiente a ese id", summary = "Trae un libro por su id", responses = {
					@ApiResponse(description = "Libro encontrado", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
					@ApiResponse(description = "Error, libro no encontrado", responseCode = "404", content = @Content(mediaType = "String", schema = @Schema(implementation = Error.class))),
					@ApiResponse(description = "Error, libro no encontrado", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))

	}

	)
	@GetMapping(value = "/{id}")
	// @ResponseStatus (HttpStatus.OK")
	public Book findById(@PathVariable long id) {
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException());
	}

	@GetMapping("/")
	public Collection<Book> findBooks() {
		return repository.getBooks();
	}

	@Operation(tags = "Metodos put", summary = "modifica el libro por su id", responses = {
			@ApiResponse(description = "Libro modificado con exito", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
			@ApiResponse(description = "Error, cliente no encontrado", responseCode = "404") })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book updateBook(@PathVariable("id") final String id, @RequestBody final Book book) {
		return book;
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book patchBook(@PathVariable("id") final String id, @RequestBody final Book book) {
		return book;
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Book postBook(@NotNull @Valid @RequestBody final Book book) {
		return book;
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "/")
	@ResponseStatus(HttpStatus.OK)
	public Book headBook() {
		return new Book();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteBook(@PathVariable final long id) {
		return id;
	}

}