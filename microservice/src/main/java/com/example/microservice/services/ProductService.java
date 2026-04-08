package com.example.microservice.services;

import com.example.microservice.entities.Product;

public interface ProductService {
    Product findById(Long id);
}
