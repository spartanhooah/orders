package net.frey.orders.repository

import net.frey.orders.entity.Customer
import net.frey.orders.entity.OrderHeader
import net.frey.orders.entity.OrderLine
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
class OrderHeaderRepositoryTest extends Specification {
    @Autowired
    OrderHeaderRepository orderHeaderRepository

    @Autowired
    ProductRepository productRepository

    Product product

    void setup() {
        product = new Product(productStatus: ProductStatus.NEW, description: "test product")
        productRepository.saveAndFlush(product)
    }

    def "test save order"() {
        given:
        def orderHeader = new OrderHeader(customer: new Customer(name: "New Customer"))

        when:
        def saved = orderHeaderRepository.save(orderHeader)

        then:
        saved
        saved.id

        when:
        def fetched = orderHeaderRepository.findById(saved.id).get()

        then:
        fetched
        fetched.id == saved.id
        fetched.createdDate
        fetched.lastModifiedDate
    }

    def "save order with order line"() {
        given:
        def orderLine = new OrderLine(quantityOrdered: 5, product: product)
        def orderHeader = new OrderHeader(customer: new Customer(name: "New Customer"))
        orderHeader.addOrderLine(orderLine)

        when:
        def saved = orderHeaderRepository.save(orderHeader)

        then:
        saved
        saved.id

        when:
        def fetched = orderHeaderRepository.findById(saved.id).get()

        then:
        fetched
        fetched.id == saved.id
        fetched.createdDate
        fetched.lastModifiedDate
        fetched.orderLines.size() == 1
    }
}
