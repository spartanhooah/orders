package net.frey.orders.repository


import net.frey.orders.entity.*
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

    @Autowired
    CustomerRepository customerRepository

    Product product

    void setup() {
        product = new Product(productStatus: ProductStatus.NEW, description: "test product")
        productRepository.saveAndFlush(product)
    }

    def "test save order"() {
        given:
        def approval = new OrderApproval(approvedBy: "Me")
        def orderHeader = new OrderHeader(customer: new Customer(name: "New Customer"), orderApproval: approval)

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
        approval.approvedBy == "Me"
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

    def "test delete cascade"() {
        given:
        def customer = customerRepository.save(new Customer(name: "New Customer"))
        def orderLine = new OrderLine(quantityOrdered: 3, product: product)
        def orderHeader = new OrderHeader(customer: customer)
        orderHeader.addOrderLine(orderLine)

        def savedOrder = orderHeaderRepository.save(orderHeader)

        orderHeaderRepository.deleteById(savedOrder.id)
        orderHeaderRepository.flush()

        when:
        def empty = orderHeaderRepository.findById(savedOrder.id)

        then:
        empty.isEmpty()
    }
}
