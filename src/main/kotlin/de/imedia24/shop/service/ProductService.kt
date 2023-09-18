package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import de.imedia24.shop.exception.ProductNotFoundException
import de.imedia24.shop.exception.ProductSaveException
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.stream.Collectors

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val product = productRepository.findBySku(sku)?.toProductResponse();
        if (product == null) {
            throw ProductNotFoundException("Product with SKU " + sku + " not found");
        }
        return product;
    }

    fun findProductBySkus(skus: List<String>): List<ProductResponse>? {
        val products = productRepository.findBySkuIn(skus)
        if (products.isNullOrEmpty()) {
            throw ProductNotFoundException("Products not found for SKUs: ${skus.joinToString(", ")}")
        }
        return products.stream().map { it.toProductResponse() }.collect(Collectors.toList())
    }

    fun save(productResponse: ProductResponse): ProductResponse {
        val existedProduct= productRepository.findBySku(productResponse.sku)?.toProductResponse();
        if (existedProduct != null){
            throw ProductSaveException("Product with SKU " + productResponse.sku + " already exist")
        }
        val productEntity =
            ProductEntity(productResponse.sku, productResponse.name, productResponse.description, productResponse.price,
                ZonedDateTime.now(),null)
        return productRepository.save(productEntity).toProductResponse()
    }

    fun update(productResponse: ProductResponse): ProductResponse {
        var toUpdateProduct= productRepository.findBySku(productResponse.sku)
        if (toUpdateProduct == null){
            throw ProductNotFoundException("Product with SKU " + productResponse.sku + " not found");
        }
        toUpdateProduct.name=productResponse.name
        toUpdateProduct.description=productResponse.description
        toUpdateProduct.price=productResponse.price
        return productRepository.save(toUpdateProduct).toProductResponse()
    }

}
