package it.my.books.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import it.my.books.api.repository.BooksRepository;
import it.my.books.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class BooksServiceImpl implements BooksService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BooksServiceImpl.class);
	
	@Autowired
	private BooksRepository bookRepository;

	@Override
	public Optional<Book> getBook(String isbn) {
		return bookRepository.findById(isbn);
	}

	@Override
	public List<Book> getAllBooks(Integer page, Integer size, String sortBy) {

		return bookRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)))
			.getContent();
	}

	@Override
	public Book createBook(Book book) {
		bookRepository.save(book);
		return book;
	}

	@Override
	public Book deleteBook(String isbn) {
		Book bookToDelete = bookRepository.findById(isbn)
				.orElseThrow(() -> new RuntimeException("Unable to delete book with id " + isbn));

		bookRepository.delete(bookToDelete);
		return bookToDelete;
	}
	
	@Override
	@Transactional
	public Book updateBook(String isbn, Book book) {
		
		LOGGER.info("updateBook() isbn= {}", isbn);
		
		// find book and throw exception if does not exists
		Book findOne = bookRepository.findById(isbn)
				.orElseThrow(() -> new RuntimeException("Unable to udpdate the book, it does not exist " + isbn));

		findOne.setAuthor(book.getAuthor());
		findOne.setTitle(book.getTitle());
		if(Objects.nonNull(book.getDescription()) && !StringUtils.isEmpty(book.getDescription())) {
			findOne.setDescription(book.getDescription());
		}
		
		bookRepository.save(findOne);
		return findOne;
	}

}
