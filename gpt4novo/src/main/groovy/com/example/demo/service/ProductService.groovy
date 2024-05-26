package com.example.demo.service

import com.example.demo.model.Product
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Value

@Service
class ProductService {

    private final String apiUrl
    private final RestTemplate restTemplate

    ProductService(@Value('${external.api.url}') String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl
        this.restTemplate = restTemplate
    }

    List<Product> getAllProducts() {
        Product[] products = restTemplate.getForObject(apiUrl, Product[])
        products?.toList() ?: []
    }
}