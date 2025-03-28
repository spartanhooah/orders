package net.frey.orders.repository

import net.frey.orders.entity.Product
import net.frey.orders.entity.ProductStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest extends Specification {
    @Autowired
    ProductRepository productRepository

    def "test save product"() {
        given:
        def product = new Product(description: "Thingamajig", productStatus: ProductStatus.IN_STOCK)

        when:
        def saved = productRepository.save(product)

        then:
        saved
        saved.id

        when:
        def fetched = productRepository.findById(saved.id).get()

        then:
        fetched
        fetched.id == saved.id
        fetched.createdDate
        fetched.lastModifiedDate
    }
}
