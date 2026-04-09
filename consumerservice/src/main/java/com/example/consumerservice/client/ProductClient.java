package com.example.consumerservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.consumerservice.dto.ProductDTO;

import reactor.core.publisher.Mono;

@Component
public class ProductClient {
    private final WebClient webClient;

    public ProductClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductDTO> getProduct(Long id) {
        return webClient.get()
                .uri("http://localhost:8001/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }
}
