package de.imedia24.shop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class ShopApplication {

	@Bean
	fun flywayMigrationStrategy(): FlywayMigrationStrategy {
		return FlywayMigrationStrategy { flyway ->  }
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<ShopApplication>(*args)
		}
	}
}
