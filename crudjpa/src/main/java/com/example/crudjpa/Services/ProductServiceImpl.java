package com.example.crudjpa.Services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.crudjpa.repositories.ProductRepository;

import org.springframework.stereotype.Service;
import com.example.crudjpa.entities.Product;
import java.util.Optional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> product = repository.findById(id);
        product.ifPresent(p -> repository.delete(p));
        return product;
    }

    @PutMapping("/update/{id}")
    public Optional<Product> update(@PathVariable Long id, @RequestBody Product details) {
        return repository.findById(id).map(p -> {
            p.setName(details.getName());
            p.setPrice(details.getPrice());
            p.setDescription(details.getDescription());
            return repository.save(p);
        });
    }

}
