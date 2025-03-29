package net.frey.orders

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("local")
class OrdersApplicationTest extends Specification {
    def "context loads"() {
        when:
        null

        then:
        noExceptionThrown()
    }
}
