package booksApi.Models;

public class Book {

    private String id;
    private String name;
    private String author_name;
    private String isbn;
    private Integer stock;

    public Book(String id, String name, String author_name, String isbn, Integer stock) {
        this.id = id;
        this.name = name;
        this.author_name = author_name;
        this.isbn = isbn;
        this.stock = stock;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}
