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
import org.springframework.validation.BindingResult;

import com.example.crudjpa.entities.Product;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product details, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

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

    public ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
