package booksApi.Controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import booksApi.Models.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    ArrayList<Book> data = new ArrayList<>(Arrays.asList(
            new Book("1", "El corredor del laberinto", "James Dashner", "ISBN-135468", 50),
            new Book("2", "Las pruebas", "James Dashner", "ISBN-5648621", 49),
            new Book("3", "Cura Mortal", "James Dashner", "ISBN-5646523", 29)
    ));

    
    public ArrayList getBooks() {
        return this.data;
    }

    @GetMapping("/{id}")
    public Book getMethodName(@PathVariable String id) {
        Book select_book = null;

        for (Book book : data) {
            if (book.getId().equals(id)) {
                select_book = new Book(book);
            }
        }

        return select_book;
    }

    @PostMapping("/add-book")
    public Book addBook(@RequestBody Book book) {
        data.add(book);
        return book;
    }

    @PutMapping("/update-book/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        for (Book item : data) {
            if (item.getId().equals(id)) {
                item.setName(book.getName());
                item.setAuthor_name(book.getAuthor_name());
                item.setIsbn(book.getIsbn());
                item.setStock(book.getStock());
                return item;
            }
        }
        return null;
    }

    @DeleteMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable String id) {

        for (Book book : data) {
            if (book.getId().equals(id)) {
                data.remove(book);
                return "Se ha eliminado con exito";
            }
        }

        return "No se ha encontrado el libro que desea eliminar";

    }
}
