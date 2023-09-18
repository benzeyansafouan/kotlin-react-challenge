package de.imedia24.shop.db.entity


import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Schema(description = "Product entity representing a product in the database")
@Entity
@Table(name = "products")
data class ProductEntity(

    @field:Schema(description = "The SKU (Stock Keeping Unit) of the product", required = true)
    @Id
    @Column(name = "sku", nullable = false)
    val sku: String,

    @field:Schema(description = "The name of the product", required = true)
    @Column(name = "name", nullable = false)
    var name: String,

    @field:Schema(description = "The description of the product")
    @Column(name = "description")
    var description: String,

    @field:Schema(description = "The price of the product", required = true)
    @Column(name = "price", nullable = false)
    var price: BigDecimal,

    @field:Schema(description = "The creation timestamp of the product", required = true)
    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: ZonedDateTime,

    @field:Schema(description = "The last update timestamp of the product")
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: ZonedDateTime?
) {
    constructor() : this("", "", "", BigDecimal.ZERO, ZonedDateTime.now(), null)
}
