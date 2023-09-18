package de.imedia24.shop.db.repository

import de.imedia24.shop.db.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@EnableJpaRepositories
interface ProductRepository : JpaRepository<ProductEntity, String> {

    fun findBySku(sku: String): ProductEntity?

    fun findBySkuIn(skus: List<String>): List<ProductEntity>?
}