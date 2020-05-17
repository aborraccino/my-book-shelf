package it.my.books.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.my.books.api.repository.BooksRepository;
import it.my.books.model.Book;

@Service
public class BooksServiceImpl implements BooksService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BooksServiceImpl.class);
	
	@Autowired
	private BooksRepository bookRepository;

	@Override
	public Optional<Book> getBook(String isbn) {
		return Optional.ofNullable(bookRepository.findOne(isbn));
	}

	@Override
	public List<Book> getAllBooks(Integer page, Integer size, String sortBy) {
		Iterable<Book> findAll = bookRepository.findAll(new PageRequest(page, size, new Sort(sortBy)));
		List<Book> result = new ArrayList<>();
		
		if(Objects.nonNull(findAll)) {
			findAll.forEach(result::add);
		}
		
		return result;
	}

	@Override
	public Book createBook(Book book) {
		bookRepository.save(book);
		return book;
	}

	@Override
	public Book deleteBook(String isbn) {
		Book deleted = bookRepository.findOne(isbn);
		if(Objects.isNull(deleted)) {
			throw new RuntimeException("Unable to delete book with id " + isbn);
		}
		bookRepository.delete(deleted);
		return deleted;
	}
	
	@Override
	@Transactional
	public Book updateBook(String isbn, Book book) {
		
		LOGGER.info("updateBook() isbn= {}", isbn);
		
		// find book and throw exception if does not exists
		Book findOne = bookRepository.findOne(isbn);
		if(Objects.isNull(findOne)) {
			LOGGER.info("Unable to udpdate the book, it does not exist {}", book);
			throw new RuntimeException("Unable to udpdate the book, it does not exist " + isbn);
		}
		
		findOne.setAuthor(book.getAuthor());
		findOne.setTitle(book.getTitle());
		if(Objects.nonNull(book.getDescription()) && !StringUtils.isEmpty(book.getDescription())) {
			findOne.setDescription(book.getDescription());
		}
		
		bookRepository.save(findOne);
		return findOne;
	}

}
