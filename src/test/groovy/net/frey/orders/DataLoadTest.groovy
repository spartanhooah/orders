package net.frey.orders

import net.frey.orders.entity.Address
import net.frey.orders.entity.Customer
import net.frey.orders.entity.OrderHeader
import net.frey.orders.entity.OrderLine
import net.frey.orders.entity.Product
import net.frey.orders.entity.ProductStatus
import net.frey.orders.repository.CustomerRepository
import net.frey.orders.repository.OrderHeaderRepository
import net.frey.orders.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import spock.lang.Ignore
import spock.lang.Specification

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DataLoadTest extends Specification {
    final static def PRODUCT_D1 = "Product 1"
    final static def PRODUCT_D2 = "Product 2"
    final static def PRODUCT_D3 = "Product 3"

    final static def TEST_CUSTOMER = "TEST CUSTOMER"

    @Autowired
    OrderHeaderRepository orderHeaderRepository

    @Autowired
    CustomerRepository customerRepository

    @Autowired
    ProductRepository productRepository

    @Ignore("only used to load dummy data")
    @Rollback(value = false)
    def "test data loader"() {
        given:
        def products = loadProducts()
        def customer = loadCustomers()

        for (i in 0..100) {
            System.out.println("Creating order #: " + i)
            saveOrder(customer, products)
        }

        expect:
        orderHeaderRepository.flush()
    }

    def "demonstrate lazy vs eager fetching"() {
        given:
        def header = orderHeaderRepository.getReferenceById(9)

        println "Order ID is $header.id"
        println "Customer name is $header.customer.name"

        expect:
        true
    }

    private def saveOrder(def customer, def products) {
        def random = new Random()

        def orderHeader = new OrderHeader(customer: customer)

        products.each {
            def orderLine = new OrderLine(product: it, quantityOrdered: random.nextInt(20))
            orderHeader.addOrderLine(orderLine)
        }

        orderHeaderRepository.save(orderHeader)
    }

    private def loadCustomers() {
        getOrSaveCustomer(TEST_CUSTOMER)
    }

    private def getOrSaveCustomer(String customerName) {
        return customerRepository.findCustomerByNameIgnoreCase(customerName)
            .orElseGet {
                def c1 = new Customer(
                    name: customerName,
                    email: "test@example.com",
                    address: new Address(
                        "123 Main",
                        "New Orleans",
                        "LA",
                        "12345"
                    )
                )

                return customerRepository.save(c1)
            }
    }

    private def loadProducts() {
        [
            getOrSaveProduct(PRODUCT_D1),
            getOrSaveProduct(PRODUCT_D2),
            getOrSaveProduct(PRODUCT_D3)
        ]
    }

    private def getOrSaveProduct(String description) {
        productRepository.findByDescription(description)
            .orElseGet {
                def p1 = new Product(description: description, productStatus: ProductStatus.NEW)

                return productRepository.save(p1)
            }
    }
}
