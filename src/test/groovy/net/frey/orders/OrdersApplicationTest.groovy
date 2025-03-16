package net.frey.orders

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OrdersApplicationTest extends Specification {
    def "context loads"() {
        when:
        null

        then:
        noExceptionThrown()
    }
}
