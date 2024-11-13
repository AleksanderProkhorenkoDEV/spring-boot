package booksApi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import booksApi.Models.Book;

@Service
public class BooksServiceImpl implements BookService {

    ArrayList<Book> data = new ArrayList<>(Arrays.asList(
            new Book("1", "El corredor del laberinto", "James Dashner", "ISBN-135468", 50),
            new Book("2", "Las pruebas", "James Dashner", "ISBN-5648621", 49),
            new Book("3", "Cura Mortal", "James Dashner", "ISBN-5646523", 29)
    ));

    // Obtener todos los libros
    @Override
    public ArrayList<Book> getBooks() {
        return this.data;
    }

    // Obtener un libro por ID
    @Override
    public Optional<Book> findBookById(String id) {
        return data.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // Agregar un libro
    @Override
    public void addBook(Book book) {
        data.add(book);
    }

    // Actualizar un libro
    @Override
    public Optional<Book> updateBook(String id, Book bookDetails) {
        Optional<Book> bookToUpdate = findBookById(id);
        bookToUpdate.ifPresent(book -> {
            book.setName(bookDetails.getName());
            book.setAuthor_name(bookDetails.getAuthor_name());
            book.setIsbn(bookDetails.getIsbn());
            book.setStock(bookDetails.getStock());
        });
        return bookToUpdate;
    }

    // Eliminar un libro
    @Override
    public boolean deleteBookById(String id) {
        return data.removeIf(book -> book.getId().equals(id));
    }
}
