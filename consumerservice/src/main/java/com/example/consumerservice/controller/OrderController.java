package com.example.consumerservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerservice.dto.ProductDTO;
import com.example.consumerservice.product_client.ProductClient;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/product/{id}")
    public Mono<ProductDTO> getProductFromOrder(@PathVariable Long id)
    {
        return productClient.getProduct(id);
    }

}
