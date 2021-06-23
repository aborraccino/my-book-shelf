package it.my.books.api.service;

import java.util.List;
import java.util.Optional;

import it.my.books.model.Book;

public interface BooksService {

	Optional<Book> getBook(String isbn);
	
	Book createBook(Book book);
	
	Book deleteBook(String isbn);

	Book updateBook(String isbn, Book book);

	List<Book> getAllBooks(Integer page, Integer size, String sortBy);
}
