package net.frey.orders.repository

import net.frey.orders.entity.Address
import net.frey.orders.entity.Customer
import net.frey.orders.entity.OrderHeader
import net.frey.orders.entity.OrderStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends Specification {
    @Autowired
    CustomerRepository customerRepository

    def "test save customer"() {
        given:
        def customer = new Customer(name: "Bob", address: new Address("", "", "", ""), phone: "123-4567", email: "someone@place.com")

        when:
        def saved = customerRepository.save(customer)

        then:
        saved

        when:
        def header = new OrderHeader(orderStatus: OrderStatus.COMPLETE)
        customer.addOrderHeader(header)

        def savedAgain = customerRepository.save(customer)

        then:
        savedAgain.orderHeaders.size() == 1
    }
}
