package com.example.crudjpa.Services;

import org.springframework.transaction.annotation.Transactional;
import com.example.crudjpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import com.example.crudjpa.entities.Product;
import java.util.Optional;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository){
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

}
