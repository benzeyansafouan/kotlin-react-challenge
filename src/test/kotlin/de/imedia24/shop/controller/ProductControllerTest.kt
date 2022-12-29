package de.imedia24.shop.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import java.math.BigDecimal


@WebMvcTest
@AutoConfigureMockMvc
internal class ProductControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var productService: ProductService

//    @Test
//    fun findProductsBySkuSuccess() {
//        val sku = "1"
//        val name = "PRODUCT_NAME"
//        val description = "PRODUCT_DESCRIPTION"
//        val price: BigDecimal = BigDecimal.ONE
//        var product: ProductResponse? = ProductResponse(sku, name, description, price)
////        every { productService.findProductBySku("1") } returns product
////        every { productService.findProductBySku("2") } returns null
//
//        mockMvc.get("/api/product?sku=1")
//            .andExpect { status { isOk() } }
//            .andExpect { content().contentType(MediaType.APPLICATION_JSON) }
//        verify(productService, times(1)).findProductBySku(sku)
//    }
//
//    @Test
//    fun findProductsBySkuFailed() {
//        val sku = "1"
//        val name = "PRODUCT_NAME"
//        val description = "PRODUCT_DESCRIPTION"
//        val price: BigDecimal = BigDecimal.ONE
//        var product: ProductResponse? = ProductResponse(sku, name, description, price)
//        every { productService.findProductBySku("1") } returns product
//        every { productService.findProductBySku("2") } returns null
//        mockMvc.get("/api/product?sku=2")
//            .andExpect { status { isBadRequest() } }
//    }
//
//    @Test
//    fun findProductsBySkusSucces() {
//        val skus = listOf("1", "2")
//        val name = "PRODUCT_NAME"
//        val description = "PRODUCT_DESCRIPTION"
//        val price: BigDecimal = BigDecimal.ONE
//        var product1 = ProductResponse(skus.get(0), name, description, price)
//        var product2 = ProductResponse(skus.get(1), name, description, price)
//        var productList: MutableList<ProductResponse> = ArrayList()
//        productList.add(product1)
//        productList.add(product2)
//        every { productService.findProductBySku("1") } returns product1
//        every { productService.findProductBySku("2") } returns product2
//        every { productService.findProductBySku("0") } returns null
//        mockMvc.get("/api/products") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(skus)
//        }
//            .andExpect { status { isOk() } }
//            .andExpect { content().contentType(MediaType.APPLICATION_JSON) }
//        verify(productService, times(1)).findProductBySkus(skus)
//    }
//
//    @Test
//    fun findProductsBySkusFailed() {
//        val skus: MutableList<String> = ArrayList()
//
//        mockMvc.get("/api/products") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(skus)
//        }
//            .andExpect { status { isBadRequest() } }
//    }
//
//
//    @Test
//    fun addProductSuccess() {
//        val sku = "1"
//        val name = "PRODUCT_NAME"
//        val description = "PRODUCT_DESCRIPTION"
//        val price: BigDecimal = BigDecimal.ONE
//        var product = ProductResponse(sku, name, description, price)
//        mockMvc.post("/api/add") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(product)
//        }.andExpect { status { isOk() } }
//        verify(productService, times(1)).save(product)
//    }
//
//    @Test
//    fun addProductFailed() {
//        mockMvc.post("/api/add") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(null)
//        }.andExpect { status { isBadRequest() } }
//    }
//
//    @Test
//    fun updateProductSuccess() {
//        val sku = "1"
//        val name = "PRODUCT_NAME"
//        val description = "PRODUCT_DESCRIPTION"
//        val price: BigDecimal = BigDecimal.ONE
//        var product = ProductResponse(sku, name, description, price)
//        mockMvc.put("/api/update") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(product)
//        }.andExpect { status { isOk() } }
//        verify(productService, times(1)).update(product)
//    }
//
//    @Test
//    fun updateProductFailed() {
//        mockMvc.put("/api/update") {
//            contentType = MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(null)
//        }.andExpect { status { isBadRequest() } }
//    }
}