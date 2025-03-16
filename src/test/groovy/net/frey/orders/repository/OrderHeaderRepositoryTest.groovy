package net.frey.orders.repository

import net.frey.orders.entity.OrderHeader
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

    def "test save order"() {
        given:
        def orderHeader = new OrderHeader(customerName: "New Customer")

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
    }
}
