package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import kotlin.streams.toList

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        TODO("to be implemented")
        return productRepository.findBySku(sku)?.toProductResponse();
    }

    fun findProductBySkus(skus: List<String>): List<ProductResponse>? {
        TODO("to be implemented")
        return productRepository.findBySkuIn(skus)?.stream()?.map { product -> product.toProductResponse() }?.toList()
    }

    fun save(productResponse: ProductResponse): ProductResponse {
        val productEntity =
            ProductEntity(productResponse.sku, productResponse.name, productResponse.description, productResponse.price,
                ZonedDateTime.now(),null)
        return productRepository.save(productEntity).toProductResponse()
    }

    fun update(productResponse: ProductResponse): ProductResponse {
        var toUpdateProduct= productRepository.findBySku(productResponse.sku)
        toUpdateProduct?.name=productResponse.name
        toUpdateProduct?.description=productResponse.description
        toUpdateProduct?.price=productResponse.price
        return productRepository.save(toUpdateProduct!!).toProductResponse()
    }

}
