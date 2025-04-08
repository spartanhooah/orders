package net.frey.orders.repository;

import java.util.Optional;
import net.frey.orders.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByNameIgnoreCase(String customerName);
}
