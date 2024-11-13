package booksApi.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import booksApi.Models.Book;
import booksApi.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {

        Optional<Book> bookOptional = bookService.findBookById(id);

        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(new Book(bookOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el libro");
        }
    }

    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();

        return ResponseEntity.created(location).body(book);
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody Book book) {
        Optional<Book> bookOptional = bookService.updateBook(id, book);

        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(new Book(bookOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha editado el libro");
        }
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        return bookService.deleteBookById(id)
                ? ResponseEntity.ok("Se ha eliminado con éxito")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el libro que desea borrar.");
    }
}
