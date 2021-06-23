package it.my.books.api.repository;

import it.my.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, String> {

}
