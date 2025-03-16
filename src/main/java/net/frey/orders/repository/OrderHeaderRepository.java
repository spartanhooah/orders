package net.frey.orders.repository;

import net.frey.orders.entity.OrderHeader;
import org.springframework.data.repository.CrudRepository;

public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Long> {}
