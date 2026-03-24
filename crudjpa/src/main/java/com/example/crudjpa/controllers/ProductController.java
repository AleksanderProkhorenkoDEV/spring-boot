package com.example.crudjpa.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.crudjpa.Services.ProductService;
import org.springframework.http.ResponseEntity;
import com.example.crudjpa.entities.Product;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Product> list() {
        return service.getAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = service.findById(id);
        return product.isPresent()
                ? ResponseEntity.ok(product.get())
                : ResponseEntity.notFound().build();

    }

    @PostMapping("create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product details) {
        Optional<Product> product = service.update(id, details);
        return product.isPresent()
                ? ResponseEntity.ok(product.get())
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = service.delete(id);
        return product.isPresent()
                ? ResponseEntity.ok(product.get())
                : ResponseEntity.notFound().build();
    }
}
