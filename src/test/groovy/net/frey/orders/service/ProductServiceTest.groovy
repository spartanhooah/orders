package net.frey.orders.service

import net.frey.orders.entity.Product
import net.frey.orders.entity.ProductStatus
import net.frey.orders.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductServiceTest extends Specification {
    @Autowired
    ProductRepository repo

    ProductService testSubject

    void setup() {
        testSubject = new ProductService(repo)
    }

    def "add and update product"() {
        given:
        def product = new Product(description: "Widget", productStatus: ProductStatus.IN_STOCK, quantityOnHand: 15)

        when:
        def savedProduct = testSubject.save(product)

        then:
        savedProduct

        and:
        when:
        def savedAgain = testSubject.updateQuantityOnHand(savedProduct.id, 5)

        then:
        savedAgain.quantityOnHand == 5
    }
}
