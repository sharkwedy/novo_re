package com.example.demo.controller

import com.example.demo.service.ProductService
import com.example.demo.model.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService

    ProductController(ProductService productService) {
        this.productService = productService
    }

    @GetMapping
    List<Product> getAllProducts() {
        productService.getAllProducts()
    }
}