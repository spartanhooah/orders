package net.frey.orders.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.frey.orders.entity.OrderHeader;
import net.frey.orders.repository.OrderHeaderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootstrapOrderService {
    private final OrderHeaderRepository orderHeaderRepository;

    @Transactional
    public void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepository.findById(55L).get();

        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        });
    }
}
