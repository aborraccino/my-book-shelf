package it.my.books.api.service;

import java.util.ArrayList;
import java.util.List;

import it.my.books.api.repository.BooksRepository;
import it.my.books.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@InjectMocks
	private BooksServiceImpl booksService;
	
	@Mock
	private BooksRepository bookRepository;
	
	@Test
	public void getAllBooksTest() {
		// given
//		List<Book> dummyBookList = createDummyBookList();
//		Mockito.when(bookRepository.findAll(any(PageRequest.class))).thenReturn(dummyBookList);
//
//		// when
//		List<Book> allBooks = booksService.getAllBooks(0, 10, "isbn");
//
//		// then
//		assertNotNull(allBooks);
//		assertEquals(dummyBookList, allBooks);
	}
	
	@Test
	public void updateBook_whenDescriptionIsEmpy_thenUpdateBook() {
		// given
//		Book dummyBook = createDummyBook("description");
//		Book dummyBookToUpd = createDummyBookWithEmpyDescription();
//		Mockito.when(bookRepository.findOne(Mockito.any())).thenReturn(dummyBook);
		
//		// when
//		List<Book> allBooks = booksService.updateBook("123", book);
//		
//		// then
//		assertNotNull(allBooks);
//		assertEquals(dummyBookList, allBooks);
	}

	private Book createDummyBook(String description) {
		Book book = new Book();
		book.setAuthor("author");
		book.setDescription(description);
		book.setTitle("title");
		return book;
	}
	
	private Book createDummyBookWithEmpyDescription() {
		return createDummyBook(null);
	}

	private List<Book> createDummyBookList() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("9780007322497", "The Fellowship of the Ring: The Lord of the Rings", "J. R. R. Tolkien"));
		books.add(new Book("9781119235583", "Java For Dummies", "Barry A. Burd"));
		books.add(new Book("9780450411434", "It", "Steven King"));
		books.add(new Book("9783842102217", "Das tolino-Buch", "Christine Peyton"));
		
		return books;
	}

}
