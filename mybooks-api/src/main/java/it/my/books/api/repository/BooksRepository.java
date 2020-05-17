package it.my.books.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.my.books.model.Book;

public interface BooksRepository extends MongoRepository<Book, String> {

}
