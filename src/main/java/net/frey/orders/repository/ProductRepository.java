package net.frey.orders.repository;

import java.util.Optional;
import net.frey.orders.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDescription(String description);
}
