package net.frey.orders.service;

import lombok.RequiredArgsConstructor;
import net.frey.orders.entity.Product;
import net.frey.orders.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product updateQuantityOnHand(Long productId, Integer quantityOnHand) {
        var found = productRepository.findById(productId).orElseThrow();

        found.setQuantityOnHand(quantityOnHand);

        return productRepository.save(found);
    }
}
