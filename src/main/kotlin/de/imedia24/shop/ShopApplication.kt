package de.imedia24.shop

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class ShopApplication

fun main(args: Array<String>) {
	@Bean
	fun flywayMigrationStrategy(): FlywayMigrationStrategy? {
		return FlywayMigrationStrategy { args: Flyway? -> }
	}
	runApplication<ShopApplication>(*args)
}
