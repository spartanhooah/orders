package net.frey.orders.bootstrap;

import lombok.RequiredArgsConstructor;
import net.frey.orders.entity.Customer;
import net.frey.orders.repository.CustomerRepository;
import net.frey.orders.repository.OrderHeaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final BootstrapOrderService bootstrapOrderService;
    private final OrderHeaderRepository orderHeaderRepository;
    private final CustomerRepository customerRepository;

    //    @Override
    //    @Transactional
    //    public void run(String... args) throws Exception {
    //        var orderHeader = orderHeaderRepository.findById(9L).get();
    //
    //        orderHeader.getOrderLines().forEach(line -> {
    //            System.out.println(line.getProduct().getDescription());
    //
    //            line.getProduct().getCategories().forEach(category -> System.out.println(category.getDescription()));
    //        });
    //    }

    @Override
    public void run(String... args) throws Exception {
        bootstrapOrderService.readOrderData();

        var customer = new Customer();
        customer.setName("Testing version");

        var savedCustomer = customerRepository.save(customer);
        System.out.println("Customer version " + savedCustomer.getVersion());

        savedCustomer.setName("Testing version 2");
        var savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("Customer version " + savedCustomer2.getVersion());

        savedCustomer2.setName("Testing version 3");
        var savedCustomer3 = customerRepository.save(savedCustomer2);

        System.out.println("Customer version " + savedCustomer3.getVersion());

        customerRepository.delete(savedCustomer3);
    }
}
