package main.kotlin.de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/test")
    fun test():ResponseEntity<String>{
        return ResponseEntity.ok("test")
    }

    @GetMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")
        val product:ProductResponse? = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products", produces = ["application/json;charset=utf-8"])
    fun findProductsBySkus(
        @RequestParam("skus") skus: List<String>
    ): ResponseEntity<List<ProductResponse>> {
        logger.info("Request for products $skus")
        val products:List<ProductResponse>? = productService.findProductBySkus(skus)
        return if(products!!.isEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(products)
        }
    }

    @PostMapping("/add")
    fun addProduct(@RequestBody product: ProductResponse):ResponseEntity<ProductResponse>{
        return try {
            var addedProduct:ProductResponse= productService.save(product)
            ResponseEntity.ok(addedProduct)
        }catch (e:Exception){
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/update")
    fun updateProduct(@RequestBody product: ProductResponse): ResponseEntity<ProductResponse>{
        return try {
            var updatedProduct:ProductResponse= productService.update(product)
            ResponseEntity.ok(updatedProduct)
        }catch (e:Exception){
            ResponseEntity.notFound().build()
        }
    }


}
