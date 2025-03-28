package net.frey.orders.entity

import spock.lang.Specification

class OrderHeaderTest extends Specification {
    def "test equals"() {
        given:
        def order1 = new OrderHeader(id: 1L)
        def order2 = new OrderHeader(id: 1L)

        expect:
        order1 == order2
    }

    def "test not equals"() {
        given:
        def order1 = new OrderHeader(id: 1L)
        def order2 = new OrderHeader(id: 2L)

        expect:
        order1 != order2
    }
}
