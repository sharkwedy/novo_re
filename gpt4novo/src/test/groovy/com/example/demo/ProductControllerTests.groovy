package com.example.demo

import com.example.demo.controller.ProductController
import com.example.demo.model.Product
import com.example.demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@WebMvcTest(ProductController)
class ProductControllerTests extends Specification {

    @Autowired
    MockMvc mockMvc

    @MockBean
    ProductService productService

    def "test getAllProducts"() {
        given:
        Product product = new Product(id: "1", name: "Test Product")
        given(productService.getAllProducts()).willReturn([product])

        expect:
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"))
    }
}