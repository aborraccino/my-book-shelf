package it.my.books.model;

import org.springframework.data.annotation.Id;

//@Entity(name="BOOK")
public class Book {
	
	@Id
    //@Column(name = "ISBN")
	private String isbn;
	
	//@Column(name = "TITLE", nullable=false, length=500)
	private String title;
	
	//@Column(name = "AUTHOR", nullable=false, length=500)
	private String author;
	
	//@Column(name = "description", length=500)
	private String description;
	
	public Book() {
	}

	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
