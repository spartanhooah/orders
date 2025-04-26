package net.frey.orders;

import lombok.RequiredArgsConstructor;
import net.frey.orders.repository.OrderHeaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final OrderHeaderRepository orderHeaderRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var orderHeader = orderHeaderRepository.findById(9L).get();

        orderHeader.getOrderLines().forEach(line -> {
            System.out.println(line.getProduct().getDescription());

            line.getProduct().getCategories().forEach(category -> System.out.println(category.getDescription()));
        });
    }
}
