package booksApi.services;

import java.util.ArrayList;
import java.util.Optional;

import booksApi.Models.Book;

public interface BookService {

    ArrayList<Book> getBooks();

    Optional<Book> findBookById(String id);

    void addBook(Book book);

    Optional<Book> updateBook(String id, Book bookDetails);

    boolean deleteBookById(String id);
}
