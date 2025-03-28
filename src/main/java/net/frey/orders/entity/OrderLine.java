package net.frey.orders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class OrderLine extends BaseEntity {
    private Integer quantityOrdered;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private OrderHeader orderHeader;
}
