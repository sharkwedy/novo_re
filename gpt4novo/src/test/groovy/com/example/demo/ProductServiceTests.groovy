package com.example.demo

import com.example.demo.model.Product
import com.example.demo.service.ProductService
import spock.lang.Specification
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.client.RestTemplate
import static org.mockito.BDDMockito.given

@SpringBootTest
class ProductServiceTests extends Specification {

    @MockBean
    RestTemplate restTemplate

    @Value('${external.api.url}')
    String apiUrl

    def "test getAllProducts"() {
        given:
        Product product = new Product(id: "1", name: "Test Product")
        restTemplate.getForObject(apiUrl, Product[]) >> [product]
        ProductService productService = new ProductService(apiUrl, restTemplate)

        when:
        List<Product> products = productService.getAllProducts()

        then:
        products.size() == 1
        products[0].name == "Test Product"
    }
}