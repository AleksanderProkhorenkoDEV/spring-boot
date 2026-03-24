package com.example.crudjpa.Services;

import com.example.crudjpa.entities.Product;
import java.util.Optional;
import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> delete(Long id);
}
