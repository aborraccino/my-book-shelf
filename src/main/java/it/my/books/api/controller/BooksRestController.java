package it.my.books.api.controller;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

import it.my.books.api.service.BooksService;
import it.my.books.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BooksRestController.class);
	
	@Autowired
	private BooksService bookService;
	
	@GetMapping("/books/{isbn}")
	public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
		
		Optional<Book> book = bookService.getBook(isbn);
		
		if(book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") @Max(value = 10) Integer size,
			@RequestParam(defaultValue = "isbn") String sortBy) {
		List<Book> allBooks = bookService.getAllBooks(page, size, sortBy);
		return new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
		
	}

	@PostMapping(value = "/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		bookService.createBook(book);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@DeleteMapping("/books/{isbn}")
	public ResponseEntity<Book> deleteBook(@PathVariable("isbn") String isbn) {
		try {
			Book deleteBook = bookService.deleteBook(isbn);
			return new ResponseEntity<>(deleteBook, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		
	}
	
	@PutMapping(value = "/books/{isbn}")
	public ResponseEntity<Book> updateBook(@PathVariable("isbn") String isbn, @RequestBody Book book) {
		LOGGER.info("updateBook() isbn = {}", isbn);
		try {
			Book updatedBook = bookService.updateBook(isbn, book);
			return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
