package de.imedia24.shop.controller


import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * The `ProductController` class handles product-related HTTP requests.
 *
 * @property productService The service responsible for product-related operations.
 */
@RestController
@RequestMapping("/api")
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    /**
     * Handles the HTTP GET request to find a product by SKU.
     *
     * @param sku The SKU of the product to find.
     * @return A ResponseEntity containing the product if found, or a 404 Not Found response if not found.
     */
    @Operation(summary = "Find a product by SKU")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Product found"),
        ApiResponse(responseCode = "404", description = "Product not found"),
        ApiResponse(responseCode = "500", description = "Internal Server Error")
    )
    @GetMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<Any> {
        logger.info("Request for product $sku")
        return try {
            val product = productService.findProductBySku(sku)
            if (product == null) {
                ResponseEntity.notFound().build()
            } else {
                ResponseEntity.ok(product)
            }
        }
        catch (e: Exception) {
            logger.info("Request for products")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error")
        }
    }

    /**
     * Handles the HTTP GET request to find products by a list of SKUs.
     *
     * @param skus The list of SKUs to search for.
     * @return A ResponseEntity containing the list of products if found, or a 404 Not Found response if not found.
     */
    @Operation(summary = "Find products by a list of SKUs")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Products found"),
        ApiResponse(responseCode = "404", description = "Products not found"),
        ApiResponse(responseCode = "500", description = "Internal Server Error")
    )
    @GetMapping("/products", produces = ["application/json;charset=utf-8"])
    fun findProductsBySkus(@RequestParam("skus") skus: List<String>): ResponseEntity<Any> {
        logger.info("Request for products $skus")
        return try {
            val products: List<ProductResponse>? = productService.findProductBySkus(skus)
            if (products == null || products.isEmpty()) {
                ResponseEntity.notFound().build()
            } else {
                ResponseEntity.ok(products)
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error")
        }
    }

    /**
     * Handles the HTTP POST request to add a new product.
     *
     * @param product The product to be added.
     * @return A ResponseEntity containing the added product or a 500 Internal Server Error response in case of an exception.
     */
    @Operation(summary = "Add a new product")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Product added"),
        ApiResponse(responseCode = "500", description = "Internal Server Error")
    )
    @PostMapping("/add")
    fun addProduct(@RequestBody product: ProductResponse): ResponseEntity<Any> {
        return try {
            val addedProduct: ProductResponse = productService.save(product)
            ResponseEntity.ok(addedProduct)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error")
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing product.
     *
     * @param product The updated product information.
     * @return A ResponseEntity containing the updated product or a 500 Internal Server Error response in case of an exception.
     */
    @Operation(summary = "Update an existing product")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Product updated"),
        ApiResponse(responseCode = "500", description = "Internal Server Error")
    )
    @PutMapping("/update")
    fun updateProduct(@RequestBody product: ProductResponse): ResponseEntity<Any> {
        return try {
            val updatedProduct: ProductResponse = productService.update(product)
            ResponseEntity.ok(updatedProduct)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error")
        }
    }
}
