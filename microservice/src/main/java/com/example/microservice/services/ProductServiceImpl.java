package com.example.microservice.services;

import org.springframework.stereotype.Service;

import com.example.microservice.entities.Product;
import com.example.microservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
