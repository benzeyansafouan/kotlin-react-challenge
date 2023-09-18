package de.imedia24.shop.test.controllerTest


import com.fasterxml.jackson.databind.ObjectMapper
import de.imedia24.shop.controller.ProductController
import de.imedia24.shop.domain.product.ProductResponse

import de.imedia24.shop.service.ProductService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith


import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
@WebMvcTest(ProductController::class)
@ExtendWith(MockitoExtension::class)
class ProductControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var productService: ProductService

    /**
     * Test for finding a product by SKU with an existing SKU.
     */
    @Test
    fun test_findProductsBySku_with_existing_SKU() {
        // Arrange
        val sku = "123"
        val productResponse = ProductResponse(sku,"PRODUCT_NAME","PRODUCT_DESCRIPTION", BigDecimal("10"))
        Mockito.`when`(productService.findProductBySku(sku)).thenReturn(productResponse)

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/$sku")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(productResponse)))
    }

    /**
     * Test for finding a product by SKU with a non-existing SKU.
     */
    @Test
    fun test_findProductsBySku_with_non_existing_SKU() {
        // Arrange
        val sku = "456"
        Mockito.`when`(productService.findProductBySku(sku)).thenReturn(null)

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/$sku")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    /**
     * Test for finding a product by SKU with an exception.
     */
    @Test
    fun test_findProductsBySku_with_exception() {
        // Arrange
        val sku = "789"
        Mockito.`when`(productService.findProductBySku(sku)).thenThrow(RuntimeException("Test exception"))

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/$sku")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(MockMvcResultMatchers.content().string("Internal Server Error"))
    }


    /**
     * Test for finding products by SKUs with existing products.
     */
    @Test
    fun test_findProductsBySkus_withExistingProducts() {
        // Arrange
        val skus = listOf("123", "456")
        val productResponses = listOf(
            ProductResponse(skus.get(0),"PRODUCT_NAME_1","PRODUCT_DESCRIPTION_1", BigDecimal("10")),
            ProductResponse(skus.get(1),"PRODUCT_NAME_2","PRODUCT_DESCRIPTION_2", BigDecimal("20"))
        )
        Mockito.`when`(productService.findProductBySkus(skus)).thenReturn(productResponses)

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
            .param("skus", "123", "456")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(productResponses)))
    }

    /**
     * Test for finding products by SKUs with no products.
     */
    @Test
    fun test_findProductsBySkus_withNoProducts() {
        // Arrange
        val skus = listOf("789", "1011")
        Mockito.`when`(productService.findProductBySkus(skus)).thenReturn(emptyList())

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
            .param("skus", "789", "1011")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    /**
     * Test for finding products by SKUs with an exception.
     */
    @Test
    fun test_findProductsBySkus_withException() {
        // Arrange
        val skus = listOf("123", "456")
        Mockito.`when`(productService.findProductBySkus(skus)).thenThrow(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Test exception"))

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
            .param("skus", "123", "456")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(MockMvcResultMatchers.content().string("Internal Server Error"))
    }


    /**
     * Test for adding a valid product.
     */
    @Test
    fun test_addProduct_withValidProduct() {
        // Arrange
        val productRequest = ProductResponse("123","PRODUCT_NAME_1","PRODUCT_DESCRIPTION_1", BigDecimal("10"))
        val addedProduct = ProductResponse("456","PRODUCT_NAME_2","PRODUCT_DESCRIPTION_2", BigDecimal("20"))
        Mockito.`when`(productService.save(productRequest)).thenReturn(addedProduct)

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
            .content(ObjectMapper().writeValueAsString(productRequest))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(addedProduct)))
    }

    /**
     * Test for adding a product with an exception.
     */
    @Test
    fun test_addProduct_withException() {
        // Arrange
        val productRequest = ProductResponse("123","PRODUCT_NAME_1","PRODUCT_DESCRIPTION_1", BigDecimal("10"))
        Mockito.`when`(productService.save(productRequest)).thenThrow(ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Test exception"))

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
            .content(ObjectMapper().writeValueAsString(productRequest))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(MockMvcResultMatchers.content().string("Internal Server Error"))
    }

    /**
     * Test for updating a valid product.
     */
    @Test
    fun test_updateProduct_withValidProduct() {
        // Arrange
        val productToUpdate = ProductResponse("123","PRODUCT_NAME_1","PRODUCT_DESCRIPTION_1", BigDecimal("10"))
        val updatedProduct = ProductResponse("123","PRODUCT_NAME_1","PRODUCT_DESCRIPTION_1", BigDecimal("10"))
        Mockito.`when`(productService.update(productToUpdate)).thenReturn(updatedProduct)

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/update")
            .content(ObjectMapper().writeValueAsString(productToUpdate))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(updatedProduct)))
    }

    /**
     * Test for updating an invalid product.
     */
    @Test
    fun test_updateProduct_withInvalidProduct() {
        // Arrange
        val invalidProduct = ProductResponse("123","PRODUCT_NAME_1","PRODUCT_DESCRIPTION_1", BigDecimal("10"))
        Mockito.`when`(productService.update(invalidProduct)).thenThrow(RuntimeException("Invalid product"))

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/update")
            .content(ObjectMapper().writeValueAsString(invalidProduct))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
            .andExpect(MockMvcResultMatchers.content().string("Internal Server Error"))
    }

}
